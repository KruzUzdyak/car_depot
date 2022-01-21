package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.dao.table_name.Table;
import com.epam.volodko.entity.user.*;
import org.flywaydb.core.internal.jdbc.RowMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ClientDaoIT extends DataBaseIT{

    private static final String FIND_CLIENT_BY_ID_QUERY = String.format(
            "SELECT * FROM %s AS u JOIN %s AS r ON u.%s = r.%s LEFT JOIN %s ci ON u.%s = ci.%s WHERE u.%s = ?;",
            Table.USERS, Table.ROLES, Column.USERS_ROLE_ID, Column.ROLES_ID, Table.CLIENT_INFO,
            Column.USERS_ID, Column.CLIENT_INFO_USER_ID, Column.USERS_ID);

    private final DAOFactory factory = DAOFactory.getInstance();

    @Before
    public void init() throws IOException, SQLException {
        fillDB(Query.SQL_CREATE_ROLES, Query.SQL_CREATE_USERS, Query.SQL_CREATE_CLIENT_INFO,
                Query.SQL_FILL_ROLES, Query.SQL_FILL_USERS, Query.SQL_FILL_CLIENT_INFO);
    }

    @Test
    public void testFindClientById() throws DAOException, SQLException {
        int userId = 3;
        Client expectedClient = getJdbcTemplate()
                .query(FIND_CLIENT_BY_ID_QUERY, clientMapper(), userId)
                .get(0);
        Client actualClient = factory.getClientDAO().findById(userId);

        assertEquals(expectedClient, actualClient);

        userId = 4;
        expectedClient = getJdbcTemplate()
                .query(FIND_CLIENT_BY_ID_QUERY, clientMapper(), userId)
                .get(0);
        actualClient = factory.getClientDAO().findById(userId);

        assertEquals(expectedClient, actualClient);
    }

    private RowMapper<Client> clientMapper(){
        return (rs) -> {
            int id = rs.getInt(Column.USERS_ID);
            String login = rs.getString(Column.USERS_LOGIN);
            String name = rs.getString(Column.USERS_NAME);
            String phone = rs.getString(Column.USERS_PHONE);
            Role role = RoleProvider.getRole(rs.getInt(Column.USERS_ROLE_ID));
            String note = rs.getString(Column.CLIENT_INFO_NOTE);
            String company = rs.getString(Column.CLIENT_INFO_COMPANY);
            return new Client(id, login, null, name, phone, role, company, note);
        };
    }
}
