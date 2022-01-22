package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.dao.table_name.Table;
import com.epam.volodko.entity.user.*;
import org.flywaydb.core.internal.jdbc.RowMapper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Theories.class)
public class DriverDaoIT extends DataBaseIT {

    private static final String FIND_DRIVER_BY_ID_QUERY = String.format(
            "SELECT * FROM %s AS u JOIN %s AS r ON u.%s = r.%s LEFT JOIN %s AS dl ON u.%s = dl.%s LEFT JOIN " +
                    "%s AS lt ON dl.%s = lt.%s WHERE u.%s = ?;",
            Table.USERS, Table.ROLES, Column.USERS_ROLE_ID, Column.ROLES_ID, Table.DRIVER_LICENSES,
            Column.USERS_ID, Column.DRIVER_LICENSES_USER_ID, Table.LICENSE_TYPES, Column.DRIVER_LICENSES_LICENSE_ID,
            Column.LICENSE_ID, Column.USERS_ID);
    private static final String FIND_DRIVER_BY_LOGIN_QUERY = String.format(
            "SELECT * FROM %s AS u JOIN %s AS r ON u.%s = r.%s LEFT JOIN %s AS dl ON u.%s = dl.%s LEFT JOIN " +
                    "%s AS lt ON dl.%s = lt.%s WHERE u.%s = ?;",
            Table.USERS, Table.ROLES, Column.USERS_ROLE_ID, Column.ROLES_ID, Table.DRIVER_LICENSES,
            Column.USERS_ID, Column.DRIVER_LICENSES_USER_ID, Table.LICENSE_TYPES, Column.DRIVER_LICENSES_LICENSE_ID,
            Column.LICENSE_ID, Column.USERS_LOGIN);
    private static final String FIND_ALL_DRIVERS_QUERY = String.format(
            "SELECT * FROM %s AS u JOIN %s AS r ON u.%s = r.%s LEFT JOIN %s dl " +
                    "ON u.%s = dl.%s LEFT JOIN %s lt ON dl.%s = lt.%s WHERE r.%s = ?",
            Table.USERS, Table.ROLES, Column.USERS_ROLE_ID, Column.ROLES_ID, Table.DRIVER_LICENSES,
            Column.USERS_ID, Column.DRIVER_LICENSES_USER_ID, Table.LICENSE_TYPES,
            Column.DRIVER_LICENSES_LICENSE_ID, Column.LICENSE_ID, Column.ROLES_ID);

    @DataPoints
    public static int[] driverIds = new int[]{5, 6};
    @DataPoints
    public static String[] logins = new String[]{"driver1", "driver2"};

    private final UserDAO<Driver> driverDAO = DAOFactory.getInstance().getDriverDAO();

    @BeforeClass
    public static void initTables() throws IOException, SQLException {
        fillDB(Query.SQL_CREATE_ROLES, Query.SQL_CREATE_USERS,
                Query.SQL_CREATE_LICENSE_TYPES, Query.SQL_CREATE_DRIVER_LICENSES,
                Query.SQL_FILL_ROLES, Query.SQL_FILL_USERS,
                Query.SQL_FILL_LICENSE_TYPES, Query.SQL_FILL_DRIVER_LICENSES);
    }

    @Theory
    public void testFindById(int id) throws DAOException, SQLException {
        Driver expectedDriver = getJdbcTemplate()
                .query(FIND_DRIVER_BY_ID_QUERY, driverMapper(), id)
                .get(0);
        Driver actualDriver = driverDAO.findById(id);

        assertEquals(expectedDriver, actualDriver);
    }

    @Theory
    public void testFindByLogin(String login) throws DAOException, SQLException {
        Driver expectedDriver = getJdbcTemplate()
                .query(FIND_DRIVER_BY_LOGIN_QUERY, driverMapper(), login)
                .get(0);
        Driver actualDriver = driverDAO.findByLogin(login);

        assertEquals(expectedDriver, actualDriver);
    }

    @Test
    public void findAll() throws DAOException, SQLException {
        List<Driver> actualDrivers = driverDAO.findAll();
        List<Driver> expectedDrivers = getJdbcTemplate()
                .query(FIND_ALL_DRIVERS_QUERY, driverMapper(), Role.DRIVER.getRoleId());

        assertEquals(expectedDrivers, actualDrivers);
    }

    private RowMapper<Driver> driverMapper() {
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
        driver.addLicense(createLicense(rs));
        while (rs.next()) {
            int nextDriverId = rs.getInt(Column.USERS_ID);
            if (id == nextDriverId) {
                driver.addLicense(createLicense(rs));
            } else {
                rs.previous();
                break;
            }
        }
    }

    private DriverLicense createLicense(ResultSet rs) throws SQLException {
        DriverLicenseType type = LicenseTypeProvider.getLicenseType(rs.getInt(Column.LICENSE_ID));
        Date obtainingDate = null;
        if (type != null) {
            obtainingDate = new Date(rs.getLong(Column.DRIVER_LICENSES_OBTAINING_DATE));
        }
        String licenseNumber = rs.getString(Column.DRIVER_LICENSES_LICENSE_NUMBER);
        return new DriverLicense(type, obtainingDate, licenseNumber);
    }


}
