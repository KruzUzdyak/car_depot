package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.DriverLicenseDAO;
import com.epam.volodko.dao.database.ConnectionPool;
import com.epam.volodko.dao.database.pool_exception.ConnectionPoolException;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.dao.table_name.Table;
import com.epam.volodko.entity.user.DriverLicense;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DriverLicenseDAOImpl extends AbstractDAO implements DriverLicenseDAO {

    private static final String SAVE_DRIVER_LICENSE_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s, %s) VALUES (?, ?, ?, ?);",
            Table.DRIVER_LICENSES, Column.DRIVER_LICENSES_USER_ID, Column.DRIVER_LICENSES_LICENSE_ID,
            Column.DRIVER_LICENSES_OBTAINING_DATE, Column.DRIVER_LICENSES_LICENSE_NUMBER);
    private static final String DELETE_DRIVER_LICENSE_BY_ID_QUERY = String.format(
            "DELETE FROM %s WHERE %s = ? AND %s = ?;",
            Table.DRIVER_LICENSES, Column.DRIVER_LICENSES_USER_ID, Column.DRIVER_LICENSES_LICENSE_ID);

    @Override
    public int saveNew(int driverId, DriverLicense license) throws DAOException {
        int rowsAffected;
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SAVE_DRIVER_LICENSE_QUERY);
            statement.setInt(1, driverId);
            statement.setInt(2, license.getLicenseType().getId());
            statement.setLong(3, license.getObtainingDate().getTime());
            statement.setString(4, license.getLicenseNumber());
            rowsAffected = statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement);
        }
        return rowsAffected;
    }

    @Override
    public int deleteById(int driverId, int licenseTypeId) throws DAOException {
        return processQuery(driverId, licenseTypeId, DELETE_DRIVER_LICENSE_BY_ID_QUERY);
    }
}
