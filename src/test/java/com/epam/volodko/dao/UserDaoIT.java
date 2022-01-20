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

public class UserDaoIT extends DataBaseIT{

    private static final String FIND_USER_BY_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s = ?;",
            Table.USERS, Column.USERS_ID);


    private final DAOFactory factory = DAOFactory.getInstance();

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
        User actualUser = factory.getUserDAO().findById(userId);

        assertEquals(expectedUser, actualUser);
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

    private RowMapper<Admin> adminMapper(){
        return (rs) -> {
            int id = rs.getInt(Column.USERS_ID);
            String login = rs.getString(Column.USERS_LOGIN);
            String name = rs.getString(Column.USERS_NAME);
            String phone = rs.getString(Column.USERS_PHONE);
            Role role = RoleProvider.getRole(rs.getInt(Column.USERS_ROLE_ID));
            String note = rs.getString(Column.ADMIN_INFO_NOTE);
            Date workSince = new Date(rs.getLong(Column.ADMIN_INFO_WORKS_SINCE));
            return new Admin(id, login, null, name, phone, role, workSince, note);
        };
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

    private RowMapper<Driver> driverMapper(){
        return (rs) -> {
            int id = rs.getInt(Column.USERS_ID);
            String login = rs.getString(Column.USERS_LOGIN);
            String name = rs.getString(Column.USERS_NAME);
            String phone = rs.getString(Column.USERS_PHONE);
            Role role = RoleProvider.getRole(rs.getInt(Column.USERS_ROLE_ID));
            Driver driver = new Driver(id, login, null, name, phone, role);
            mapDriverLicenses(rs, driver);

            return driver;
        };
    }

    private void mapDriverLicenses(ResultSet rs, Driver driver) throws SQLException {
        int id = driver.getId();
        while (rs.next()){
            int nextDriverId = rs.getInt(Column.USERS_ID);
            if (id == nextDriverId){
                DriverLicenseType licenseType =
                        LicenseTypeProvider.getLicenseType(rs.getInt(Column.DRIVER_LICENSES_LICENSE_ID));
                Date obtainingDate = new Date(rs.getLong(Column.DRIVER_LICENSES_OBTAINING_DATE));
                String licenseNumber = rs.getString(Column.DRIVER_LICENSES_LICENSE_NUMBER);
                driver.addLicense(new DriverLicense(licenseType, obtainingDate, licenseNumber));
            } else {
                rs.previous();
                break;
            }
        }
    }
}
