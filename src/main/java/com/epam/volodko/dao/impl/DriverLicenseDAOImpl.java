package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.dao.table_name.Table;
import com.epam.volodko.entity.user.Driver;
import com.epam.volodko.entity.user.DriverLicense;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DriverLicenseDAOImpl{

    private static final String SAVE_NEW_DRIVER_LICENSE_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s, %s) VALUES ((SELECT %s FROM %s WHERE %s = ?), ?, ?, ?);",
            Table.DRIVER_LICENSES, Column.DRIVER_LICENSES_USER_ID, Column.DRIVER_LICENSES_LICENSE_ID,
            Column.DRIVER_LICENSES_OBTAINING_DATE, Column.DRIVER_LICENSES_LICENSE_NUMBER, Column.USERS_ID,
            Table.USERS, Column.USERS_LOGIN);

    void prepareSaveDriverLicensesStatement(Driver driver, Connection connection,
                                            PreparedStatement statement) throws SQLException {
        for (DriverLicense license : driver.getLicenses()) {
            statement = connection.prepareStatement(SAVE_NEW_DRIVER_LICENSE_QUERY);
            statement.setString(1, driver.getLogin());
            statement.setInt(2, license.getLicenseType().getId());
            statement.setLong(3, license.getObtainingDate().getTime());
            statement.setString(4, license.getLicenseNumber());
            statement.executeUpdate();
        }
    }

}
