package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.dao.table_name.Table;
import com.epam.volodko.entity.user.Admin;
import com.epam.volodko.entity.user.Role;
import com.epam.volodko.entity.user.RoleProvider;
import org.flywaydb.core.internal.jdbc.RowMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class AdminDaoIT extends DataBaseIT{

    private static final String FIND_ADMIN_BY_ID_QUERY = String.format(
            "SELECT * FROM %s AS u JOIN %s AS r ON u.%s = r.%s LEFT JOIN %s ai ON u.%s = ai.%s WHERE u.%s = ?;",
            Table.USERS, Table.ROLES, Column.USERS_ROLE_ID, Column.ROLES_ID, Table.ADMIN_INFO,
            Column.USERS_ID, Column.ADMIN_INFO_USER_ID, Column.USERS_ID);
    private final DAOFactory factory = DAOFactory.getInstance();

    @Before
    public void init() throws IOException, SQLException {
        fillDB(Query.SQL_CREATE_ROLES, Query.SQL_CREATE_USERS, Query.SQL_CREATE_ADMIN_INFO,
                Query.SQL_FILL_ROLES, Query.SQL_FILL_USERS, Query.SQL_FILL_ADMIN_INFO);
    }

    @Test
    public void testFindAdminById() throws SQLException, DAOException {
        int userId = 1;
        Admin expectedAdmin = getJdbcTemplate()
                .query(FIND_ADMIN_BY_ID_QUERY, adminMapper(), userId)
                .get(0);
        Admin actualAdmin = factory.getAdminDAO().findById(userId);

        assertEquals(expectedAdmin, actualAdmin);

        userId = 2;
        expectedAdmin = getJdbcTemplate()
                .query(FIND_ADMIN_BY_ID_QUERY, adminMapper(), userId)
                .get(0);
        actualAdmin = factory.getAdminDAO().findById(userId);

        assertEquals(expectedAdmin, actualAdmin);
    }

    private RowMapper<Admin> adminMapper(){
        return (rs) -> {
            int id = rs.getInt(Column.USERS_ID);
            String login = rs.getString(Column.USERS_LOGIN);
            String name = rs.getString(Column.USERS_NAME);
            String phone = rs.getString(Column.USERS_PHONE);
            Role role = RoleProvider.getRole(rs.getInt(Column.USERS_ROLE_ID));
            String note = rs.getString(Column.ADMIN_INFO_NOTE);
            long workSince = rs.getLong(Column.ADMIN_INFO_WORKS_SINCE);
            Date date = null;
            if (workSince > 0){
                date = new Date(workSince);
            }
            return new Admin(id, login, null, name, phone, role, date, note);
        };
    }
}
