package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.dao.table_name.Table;
import com.epam.volodko.entity.user.Client;
import com.epam.volodko.entity.user.Role;
import com.epam.volodko.entity.user.RoleProvider;
import org.flywaydb.core.internal.jdbc.RowMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Theories.class)
public class ClientDaoIT extends DataBaseIT{

    private static final String FIND_CLIENT_BY_ID_QUERY = String.format(
            "SELECT * FROM %s AS u JOIN %s AS r ON u.%s = r.%s LEFT JOIN %s ci ON u.%s = ci.%s WHERE u.%s = ?;",
            Table.USERS, Table.ROLES, Column.USERS_ROLE_ID, Column.ROLES_ID, Table.CLIENT_INFO,
            Column.USERS_ID, Column.CLIENT_INFO_USER_ID, Column.USERS_ID);
    private static final String FIND_CLIENT_BY_LOGIN_QUERY = String.format(
            "SELECT * FROM %s AS u JOIN %s AS r ON u.%s = r.%s LEFT JOIN %s ci ON u.%s = ci.%s WHERE u.%s = ?;",
            Table.USERS, Table.ROLES, Column.USERS_ROLE_ID, Column.ROLES_ID, Table.CLIENT_INFO,
            Column.USERS_ID, Column.CLIENT_INFO_USER_ID, Column.USERS_LOGIN);
    private static final String FIND_ALL_CLIENTS_QUERY = String.format(
            "SELECT * FROM %s AS u JOIN %s AS r ON u.%s = r.%s LEFT JOIN %s ci ON u.%s = ci.%s WHERE r.%s = ?;",
            Table.USERS, Table.ROLES, Column.USERS_ROLE_ID, Column.ROLES_ID, Table.CLIENT_INFO,
            Column.USERS_ID, Column.CLIENT_INFO_USER_ID, Column.ROLES_ID);

    @DataPoints
    public static int[] ids = new int[]{3, 4};
    @DataPoints
    public static String[] logins = new String[]{"client1", "client2"};

    private final UserDAO<Client> clientDAO = DAOFactory.getInstance().getClientDAO();

    @Before
    public void init() throws IOException, SQLException {
        fillDB(Query.SQL_CREATE_ROLES, Query.SQL_CREATE_USERS, Query.SQL_CREATE_CLIENT_INFO,
                Query.SQL_FILL_ROLES, Query.SQL_FILL_USERS, Query.SQL_FILL_CLIENT_INFO);
    }

    @Theory
    public void testFindById(int id) throws DAOException, SQLException {
        Client expectedClient = getJdbcTemplate()
                .query(FIND_CLIENT_BY_ID_QUERY, clientMapper(), id)
                .get(0);
        Client actualClient = clientDAO.findById(id);

        assertEquals(expectedClient, actualClient);
    }

    @Theory
    public void testFindByLogin(String login) throws DAOException, SQLException{
        Client expectedClient = getJdbcTemplate()
                .query(FIND_CLIENT_BY_LOGIN_QUERY, clientMapper(), login)
                .get(0);
        Client actualClient = clientDAO.findByLogin(login);

        assertEquals(expectedClient, actualClient);
    }

    @Test
    public void testFindALl() throws DAOException, SQLException{
        List<Client> expectedClients = getJdbcTemplate()
                .query(FIND_ALL_CLIENTS_QUERY, clientMapper(), Role.CLIENT.getRoleId());
        List<Client> actualClients = clientDAO.findAll();

        assertEquals(expectedClients, actualClients);
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
