package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.dao.table_name.Table;
import com.epam.volodko.entity.user.Role;
import com.epam.volodko.entity.user.RoleProvider;
import com.epam.volodko.entity.user.User;
import org.flywaydb.core.internal.jdbc.RowMapper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Theories.class)
public class UserDaoIT extends DataBaseIT {

    private static final String FIND_USER_BY_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s = ?;",
            Table.USERS, Column.USERS_ID);
    private static final String FIND_USER_BY_LOGIN_QUERY = String.format(
            "SELECT * FROM %s AS u JOIN %s AS r ON u.%s=r.%s WHERE u.%s=?;",
            Table.USERS, Table.ROLES,
            Column.USERS_ROLE_ID, Column.ROLES_ID, Column.USERS_LOGIN);
    private static final String FIND_ALL_USERS_QUERY = String.format(
            "SELECT * FROM %s AS u JOIN %s AS r ON u.%s=r.%s;",
            Table.USERS, Table.ROLES,
            Column.USERS_ROLE_ID, Column.ROLES_ID);
    private static final String FIND_PASSWORD_BY_LOGIN_QUERY = String.format(
            "SELECT %s FROM %s WHERE %s=?;",
            Column.USERS_PASSWORD, Table.USERS, Column.USERS_LOGIN);
    private static final String FIND_PASSWORD_BY_ID_QUERY = String.format(
            "SELECT %s FROM %s WHERE %s=?;",
            Column.USERS_PASSWORD, Table.USERS, Column.USERS_ID);
    private static final String FIND_LOGIN_BY_ID_QUERY = String.format(
            "SELECT %s FROM %s WHERE %s=?;",
            Column.USERS_LOGIN, Table.USERS, Column.USERS_ID);

    @DataPoints
    public static String[] logins = new String[]{"admin1", "admin2", "client1", "client2", "driver1", "driver2"};
    @DataPoints
    public static int[] userIds = new int[]{1, 2, 3, 4, 5, 6};
    @DataPoints
    public static Object[][] userData = new Object[][]{
            {1, "admin10"},
            {2, "admin20"},
            {3, "client10"},
            {4, "client20"},
            {5, "driver10"},
            {6, "driver20"}
    };

    private final UserDAO<User> userDAO = DAOFactory.getInstance().getUserDAO();

    @BeforeClass
    public static void initTables() throws IOException, SQLException {
        fillDB(Query.SQL_CREATE_ROLES, Query.SQL_CREATE_USERS,
                Query.SQL_FILL_ROLES, Query.SQL_FILL_USERS);
    }

    @Theory
    public void testFindById(int userId) throws DAOException, SQLException {
        User expectedUser = getJdbcTemplate()
                .query(FIND_USER_BY_ID_QUERY, userMapper(), userId)
                .get(0);
        User actualUser = userDAO.findById(userId);

        assertEquals(expectedUser, actualUser);
    }

    @Theory
    public void testDeleteById(int userId) throws DAOException, SQLException, IOException {
        int rowsAffected = userDAO.deleteById(userId);
        List<User> users = getJdbcTemplate()
                .query(FIND_USER_BY_ID_QUERY, userMapper(), userId);

        assertEquals(1, rowsAffected);
        assertTrue(users.isEmpty());

        cleanDB();
        initTables();
    }

    @Theory
    public void testFindByLogin(String login) throws SQLException, DAOException {
        User expectedUser = getJdbcTemplate()
                .query(FIND_USER_BY_LOGIN_QUERY, userMapper(), login)
                .get(0);
        User actualUser = userDAO.findByLogin(login);

        assertEquals(expectedUser, actualUser);
    }

    @Theory
    public void testFindPasswordHashByLogin(String login) throws SQLException, DAOException {
        String expectedHash = getJdbcTemplate()
                .query(FIND_PASSWORD_BY_LOGIN_QUERY, (rs) -> rs.getString(Column.USERS_PASSWORD), login)
                .get(0);
        String actualHash = userDAO.findPasswordHashByLogin(login);

        assertEquals(expectedHash, actualHash);
    }

    @Theory
    public void testUpdateLogin(Object[] userData) throws DAOException, SQLException, IOException {
        int userId = (int) userData[0];
        String newLogin = (String) userData[1];
        int rowsAffected = userDAO.updateLogin(userId, newLogin);
        String actualLogin = getJdbcTemplate()
                .query(FIND_LOGIN_BY_ID_QUERY, (rs) -> rs.getString(Column.USERS_LOGIN), userId)
                .get(0);

        assertEquals(1, rowsAffected);
        assertEquals(newLogin, actualLogin);

        cleanDB();
        initTables();
    }

    @Theory
    public void testUpdatePassword(Object[] userData) throws DAOException, SQLException{
        int userId = (int) userData[0];
        String newPass = (String) userData[1];
        int rowsAffected = userDAO.updatePassword(userId, newPass);
        String actualLogin = getJdbcTemplate()
                .query(FIND_PASSWORD_BY_ID_QUERY, (rs) -> rs.getString(Column.USERS_PASSWORD), userId)
                .get(0);

        assertEquals(1, rowsAffected);
        assertEquals(newPass, actualLogin);
    }

    @Test
    public void testFindAll() throws SQLException, DAOException {
        List<User> expectedUsers = getJdbcTemplate()
                .query(FIND_ALL_USERS_QUERY, userMapper());
        List<User> actualUsers = userDAO.findAll();

        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    public void testSaveInfoDisabled() throws DAOException {
        int result = userDAO.saveInfo(9000);
        assertEquals(0, result);
    }

    @Test
    public void testUpdateInfoDisabled() throws DAOException {
        int result = userDAO.updateInfo(new User());
        assertEquals(0, result);
    }

    @Test
    public void testSaveNew() throws DAOException, SQLException {
        int initialId = -1;
        User expectedUser = new User(initialId, "admin login",
                "stub", "admin name", "+3766684", Role.ADMIN);
        int rowsAffected = userDAO.saveNew(expectedUser);
        expectedUser.setPassword(null);
        User actualUser = getJdbcTemplate()
                .query(FIND_USER_BY_ID_QUERY, userMapper(), expectedUser.getId())
                .get(0);

        assertEquals(1, rowsAffected);
        assertEquals(expectedUser, actualUser);
    }

    @Test(expected = DAOException.class)
    public void testDAOExceptionOnWrongUserData() throws DAOException {
        userDAO.saveNew(new User(0, null, null, null, null, Role.ADMIN));
    }

    @Test
    public void testUpdateUser() throws SQLException, DAOException {
        User user = getJdbcTemplate()
                .query(FIND_USER_BY_ID_QUERY, userMapper(), 1)
                .get(0);
        user.setName("new");
        user.setPhone("new");
        User expectedUser = new User(user.getId(), user.getLogin(),
                user.getPassword(), user.getName(), user.getPhone(), user.getRole());
        user.setPassword("new");
        user.setLogin("new");
        user.setRole(Role.DRIVER);
        int rowsAffected = userDAO.update(user);
        User actualUser = getJdbcTemplate()
                .query(FIND_USER_BY_ID_QUERY, userMapper(), expectedUser.getId())
                .get(0);

        assertEquals(1, rowsAffected);
        assertEquals(expectedUser, actualUser);
    }


    private RowMapper<User> userMapper() {
        return (rs) -> {
            int id = rs.getInt(Column.USERS_ID);
            String login = rs.getString(Column.USERS_LOGIN);
            String name = rs.getString(Column.USERS_NAME);
            String phone = rs.getString(Column.USERS_PHONE);
            Role role = RoleProvider.getRole(rs.getInt(Column.USERS_ROLE_ID));
            return new User(id, login, null, name, phone, role);
        };
    }
}
