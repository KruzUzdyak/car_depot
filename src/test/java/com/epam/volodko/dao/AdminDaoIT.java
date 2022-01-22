package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.dao.table_name.Table;
import com.epam.volodko.entity.user.Admin;
import com.epam.volodko.entity.user.Role;
import com.epam.volodko.entity.user.RoleProvider;
import org.flywaydb.core.internal.jdbc.RowMapper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Theories.class)
public class AdminDaoIT extends DataBaseIT {

    private static final String FIND_ADMIN_BY_ID_QUERY = String.format(
            "SELECT * FROM %s AS u JOIN %s AS r ON u.%s = r.%s LEFT JOIN %s ai ON u.%s = ai.%s WHERE u.%s = ?;",
            Table.USERS, Table.ROLES, Column.USERS_ROLE_ID, Column.ROLES_ID, Table.ADMIN_INFO,
            Column.USERS_ID, Column.ADMIN_INFO_USER_ID, Column.USERS_ID);
    private static final String FIND_ADMIN_BY_LOGIN_QUERY = String.format(
            "SELECT * FROM %s AS u JOIN %s AS r ON u.%s = r.%s LEFT JOIN %s ai ON u.%s = ai.%s WHERE u.%s = ?;",
            Table.USERS, Table.ROLES, Column.USERS_ROLE_ID, Column.ROLES_ID, Table.ADMIN_INFO,
            Column.USERS_ID, Column.ADMIN_INFO_USER_ID, Column.USERS_LOGIN);
    private static final String FIND_ALL_ADMINS_QUERY = String.format(
            "SELECT * FROM %s AS u JOIN %s AS r ON u.%s = r.%s LEFT JOIN %s ai ON u.%s = ai.%s WHERE r.%s = ?;",
            Table.USERS, Table.ROLES, Column.USERS_ROLE_ID, Column.ROLES_ID, Table.ADMIN_INFO,
            Column.USERS_ID, Column.ADMIN_INFO_USER_ID, Column.ROLES_ID);

    @DataPoints
    public static int[] adminIds = new int[]{1, 2};
    @DataPoints
    public static String[] logins = new String[]{"admin1", "admin2"};

    private final UserDAO<Admin> adminDAO = DAOFactory.getInstance().getUserDAO(Role.ADMIN);

    @BeforeClass
    public static void init() throws IOException, SQLException {
        fillDB(Query.SQL_CREATE_ROLES, Query.SQL_CREATE_USERS, Query.SQL_CREATE_ADMIN_INFO,
                Query.SQL_FILL_ROLES, Query.SQL_FILL_USERS, Query.SQL_FILL_ADMIN_INFO);
    }

    @Theory
    public void testFindById(int adminId) throws SQLException, DAOException {
        Admin expectedAdmin = getJdbcTemplate()
                .query(FIND_ADMIN_BY_ID_QUERY, adminMapper(), adminId)
                .get(0);
        Admin actualAdmin = adminDAO.findById(adminId);

        assertEquals(expectedAdmin, actualAdmin);
    }

    @Theory
    public void testFindByLogin(String login) throws DAOException, SQLException {
        Admin expectedAdmin = getJdbcTemplate()
                .query(FIND_ADMIN_BY_LOGIN_QUERY, adminMapper(), login)
                .get(0);
        Admin actualAdmin = adminDAO.findByLogin(login);

        assertEquals(expectedAdmin, actualAdmin);
    }

    @Test
    public void testFindAll() throws DAOException, SQLException {
        List<Admin> expectedAdmins = getJdbcTemplate()
                .query(FIND_ALL_ADMINS_QUERY, adminMapper(), Role.ADMIN.getRoleId());
        List<Admin> actualAdmins = adminDAO.findAll();

        assertEquals(expectedAdmins, actualAdmins);
    }

    @Test
    public void testSaveInfo() throws DAOException, SQLException {
        Admin admin = new Admin(0, "stub", "stub", "stub",
                "stub", Role.ADMIN, null, null);
        adminDAO.saveNew(admin);
        admin.setPassword(null);
        int rowsAffected = adminDAO.saveInfo(admin.getId());
        Admin actualAdmin = getJdbcTemplate()
                .query(FIND_ADMIN_BY_ID_QUERY, adminMapper(), admin.getId())
                .get(0);

        assertEquals(1, rowsAffected);
        assertEquals(admin, actualAdmin);
    }

    @Test
    public void testUpdateInfo() throws DAOException, SQLException {
        Admin admin = getJdbcTemplate()
                .query(FIND_ADMIN_BY_ID_QUERY, adminMapper(), 1)
                .get(0);
        admin.setNote("new note");
        admin.setWorksSince(new Date(10101010101010L));
        Admin expectedAdmin = new Admin(admin.getId(), admin.getLogin(), admin.getPassword(), admin.getName(),
                admin.getPhone(), admin.getRole(), admin.getWorksSince(), admin.getNote());
        admin.setLogin("stub");
        admin.setPhone("stub");
        int rowsAffected = adminDAO.updateInfo(admin);
        Admin actualAdmin = getJdbcTemplate()
                .query(FIND_ADMIN_BY_ID_QUERY, adminMapper(), expectedAdmin.getId())
                .get(0);

        assertEquals(1, rowsAffected);
        assertEquals(expectedAdmin, actualAdmin);
    }

    private RowMapper<Admin> adminMapper() {
        return (rs) -> {
            int id = rs.getInt(Column.USERS_ID);
            String login = rs.getString(Column.USERS_LOGIN);
            String name = rs.getString(Column.USERS_NAME);
            String phone = rs.getString(Column.USERS_PHONE);
            Role role = RoleProvider.getRole(rs.getInt(Column.USERS_ROLE_ID));
            String note = rs.getString(Column.ADMIN_INFO_NOTE);
            long workSince = rs.getLong(Column.ADMIN_INFO_WORKS_SINCE);
            Date date = null;
            if (workSince > 0) {
                date = new Date(workSince);
            }
            return new Admin(id, login, null, name, phone, role, date, note);
        };
    }
}
