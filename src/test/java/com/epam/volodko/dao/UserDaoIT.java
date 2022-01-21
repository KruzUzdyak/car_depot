package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.dao.table_name.Table;
import com.epam.volodko.entity.user.Role;
import com.epam.volodko.entity.user.RoleProvider;
import com.epam.volodko.entity.user.User;
import org.flywaydb.core.internal.jdbc.RowMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserDaoIT extends DataBaseIT{

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

    private final UserDAO<User> userDAO = DAOFactory.getInstance().getUserDAO();

    @Before
    public void init() throws IOException, SQLException {
        fillDB(Query.SQL_CREATE_ROLES, Query.SQL_CREATE_USERS,
                Query.SQL_FILL_ROLES, Query.SQL_FILL_USERS);
    }

    @Test
    public void testFindById() throws DAOException, SQLException {
        int userId = 1;
        User expectedUser = getJdbcTemplate()
                .query(FIND_USER_BY_ID_QUERY, userMapper(), userId)
                .get(0);
        User actualUser = userDAO.findById(userId);

        assertEquals(expectedUser, actualUser);

        userId = 3;
        expectedUser = getJdbcTemplate()
                .query(FIND_USER_BY_ID_QUERY, userMapper(), userId)
                .get(0);
        actualUser = userDAO.findById(userId);

        assertEquals(expectedUser, actualUser);

        userId = 5;
        expectedUser = getJdbcTemplate()
                .query(FIND_USER_BY_ID_QUERY, userMapper(), userId)
                .get(0);
        actualUser = userDAO.findById(userId);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testFindByLogin() throws SQLException, DAOException {
        String login = "admin1";
        User expectedUser = getJdbcTemplate()
                .query(FIND_USER_BY_LOGIN_QUERY, userMapper(), login)
                .get(0);
        User actualUser = userDAO.findByLogin(login);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testFindAll() throws SQLException, DAOException{
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

    private RowMapper<User> userMapper(){
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
