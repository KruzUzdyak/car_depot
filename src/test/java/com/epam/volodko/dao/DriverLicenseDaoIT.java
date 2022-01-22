package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.dao.table_name.Table;
import com.epam.volodko.entity.user.DriverLicense;
import com.epam.volodko.entity.user.DriverLicenseType;
import com.epam.volodko.entity.user.LicenseTypeProvider;
import org.flywaydb.core.internal.jdbc.RowMapper;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DriverLicenseDaoIT extends DataBaseIT {

    private static final String GET_DRIVER_LICENSE = String.format(
            "SELECT * FROM %s WHERE %s=? AND %s=?;",
            Table.DRIVER_LICENSES, Column.DRIVER_LICENSES_USER_ID, Column.DRIVER_LICENSES_LICENSE_ID);

    private final DriverLicenseDAO licenseDAO = DAOFactory.getInstance().getLicenseDAO();

    @BeforeClass
    public static void initTables() throws IOException, SQLException {
        fillDB(Query.SQL_CREATE_LICENSE_TYPES, Query.SQL_CREATE_ROLES,
                Query.SQL_CREATE_USERS, Query.SQL_CREATE_DRIVER_LICENSES);
        fillDB(Query.SQL_FILL_ROLES, Query.SQL_FILL_LICENSE_TYPES,
                Query.SQL_FILL_USERS, Query.SQL_FILL_DRIVER_LICENSES);
    }

    @Test
    public void testSaveNew() throws DAOException, SQLException {
        int driverID = 5;
        DriverLicenseType licenseType = DriverLicenseType.A1;
        DriverLicense expectedLicense =
                new DriverLicense(licenseType, new Date(), "TestNumber123");

        int rowsAffected = licenseDAO.saveNew(driverID, expectedLicense);
        int expectedAffect = 1;

        DriverLicense actualLicense = getJdbcTemplate()
                .query(GET_DRIVER_LICENSE, licenseMapper(), driverID, licenseType.getId())
                .get(0);

        assertEquals(expectedAffect, rowsAffected);
        assertEquals(expectedLicense, actualLicense);
    }

    @Test(expected = DAOException.class)
    public void testSaveNewWrongDriverId() throws DAOException {
        DriverLicense license =
                new DriverLicense(DriverLicenseType.A1, new Date(), "123qwe");
        licenseDAO.saveNew(-1, license);
    }

    @Test
    public void testDeleteById() throws DAOException, SQLException, IOException {
        int driverId = 6;
        int licenseTypeId = DriverLicenseType.CE.getId();

        int rowsAffected = licenseDAO.deleteById(driverId, licenseTypeId);
        int expectedAffect = 1;

        List<DriverLicense> licenses = getJdbcTemplate()
                .query(GET_DRIVER_LICENSE, licenseMapper(), driverId, licenseTypeId);

        assertEquals(expectedAffect, rowsAffected);
        assertTrue(licenses.isEmpty());

        cleanDB();
        initTables();
    }

    private RowMapper<DriverLicense> licenseMapper(){
        return (rs) -> {
            DriverLicenseType licenseType = LicenseTypeProvider
                    .getLicenseType(rs.getInt(Column.DRIVER_LICENSES_LICENSE_ID));
            Date obtainingDate = new Date(rs.getLong(Column.DRIVER_LICENSES_OBTAINING_DATE));
            String licenseNumber = rs.getString(Column.DRIVER_LICENSES_LICENSE_NUMBER);
            return new DriverLicense(licenseType, obtainingDate, licenseNumber);
        };
    }
}
