package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.exception.DAOException;
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
    private static final String DELETE_DRIVER_LICENSE_BY_ID_QUERY = String.format(
            "DELETE FROM %s WHERE %s = ?;",
            Table.DRIVER_LICENSES, Column.DRIVER_LICENSES_USER_ID);

    void prepareSaveDriverLicensesStatement(Driver driver, Connection connection) throws DAOException {
        try {
            for (DriverLicense license : driver.getLicenses()) {
                PreparedStatement statement = connection.prepareStatement(SAVE_NEW_DRIVER_LICENSE_QUERY);
                statement.setString(1, driver.getLogin());
                statement.setInt(2, license.getLicenseType().getId());
                statement.setLong(3, license.getObtainingDate().getTime());
                statement.setString(4, license.getLicenseNumber());
                statement.executeUpdate();
            }
        } catch (SQLException e){
            throw new DAOException("SQLException when preparing for Save driver licenses in statement.", e);
        }
    }

    void prepareUpdateDriverLicensesStatement(Driver driver, Connection connection) throws DAOException{
        prepareDeleteDriverLicensesStatement(driver, connection);
        prepareSaveDriverLicensesStatement(driver, connection);
    }

    void prepareDeleteDriverLicensesStatement(Driver driver, Connection connection) throws DAOException{
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_DRIVER_LICENSE_BY_ID_QUERY);
            statement.setInt(1, driver.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQLException while try to delete driver licenses.", e);
        }

    }

}
