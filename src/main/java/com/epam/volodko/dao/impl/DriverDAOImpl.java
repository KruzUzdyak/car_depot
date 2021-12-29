package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.UserDAO;
import com.epam.volodko.dao.builder.BuilderFactory;
import com.epam.volodko.dao.database.ConnectionPool;
import com.epam.volodko.dao.database.pool_exception.ConnectionPoolException;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.dao.table_name.Table;
import com.epam.volodko.entity.user.Driver;
import com.epam.volodko.entity.user.DriverLicense;
import com.epam.volodko.entity.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverDAOImpl extends AbstractUserDAO {

    private static final String FIND_DRIVER_BY_ID_QUERY = String.format(
            "SELECT * FROM %s AS u JOIN %s AS r ON u.%s = r.%s JOIN %s AS dl ON u.%s = dl.%s JOIN " +
                    "%s AS lt ON dl.%s = lt.%s WHERE u.%s = ?;",
            Table.USERS, Table.ROLES, Column.USERS_ROLE_ID, Column.ROLES_ID, Table.DRIVER_LICENSES,
            Column.USERS_ID, Column.DRIVER_LICENSES_USER_ID, Table.LICENSE_TYPES, Column.DRIVER_LICENSES_LICENSE_ID,
            Column.LICENSE_ID, Column.USERS_ID);
    private static final String FIND_DRIVER_BY_LOGIN_QUERY = String.format(
            "SELECT * FROM %s AS u JOIN %s AS r ON u.%s = r.%s JOIN %s AS dl ON u.%s = dl.%s JOIN " +
                    "%s AS lt ON dl.%s = lt.%s WHERE u.%s = ?;",
            Table.USERS, Table.ROLES, Column.USERS_ROLE_ID, Column.ROLES_ID, Table.DRIVER_LICENSES,
            Column.USERS_ID, Column.DRIVER_LICENSES_USER_ID, Table.LICENSE_TYPES, Column.DRIVER_LICENSES_LICENSE_ID,
            Column.LICENSE_ID, Column.USERS_LOGIN);
    private static final String FIND_ALL_DRIVERS_QUERY = String.format(
            "SELECT * FROM %s AS u JOIN %s AS r ON u.%s = r.%s JOIN %s dl " +
                    "ON u.%s = dl.%s JOIN %s lt ON dl.%s = lt.%s",
            Table.USERS, Table.ROLES, Column.USERS_ROLE_ID, Column.ROLES_ID, Table.DRIVER_LICENSES,
            Column.USERS_ID, Column.DRIVER_LICENSES_USER_ID, Table.LICENSE_TYPES,
            Column.DRIVER_LICENSES_LICENSE_ID, Column.LICENSE_ID);

    DriverLicenseDAOImpl licenseDAO = new DriverLicenseDAOImpl();
/*
    @Override
    Driver findById(int userId) throws DAOException {
        Driver driver;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(FIND_DRIVER_BY_ID_QUERY,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                driver = BuilderFactory.getDriverBuilder().build(resultSet);
            } else {
                driver = new Driver();
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException("SQLException when try to find driver by id.", e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return driver;
    }

    @Override
    Driver findByLogin(String userLogin) throws DAOException {
        Driver driver;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(FIND_DRIVER_BY_LOGIN_QUERY,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.setString(1, userLogin);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                driver = BuilderFactory.getDriverBuilder().build(resultSet);
            } else {
                driver = new Driver();
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException("SQLException when try to find driver by login.", e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return driver;    }

    @Override
    List<Driver> findAll() throws DAOException {
        List<Driver> drivers = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(FIND_ALL_DRIVERS_QUERY,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                drivers.add(BuilderFactory.getDriverBuilder().build(resultSet));
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException("SQLException when try to find all drivers.", e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return drivers;
    }

    @Override
    public int saveNewUser(Driver driver) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        int rowsAffected;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SAVE_NEW_USER_QUERY);
            prepareSaveUserStatement(driver, statement);
            rowsAffected = statement.executeUpdate();
            licenseDAO.prepareSaveDriverLicensesStatement(driver, connection);
            int userId = getLastAddedUserId(driver, connection, statement);
            driver.setUserId(userId);
            connection.commit();
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException("SQLException when saving new driver.", e);
        } finally {
            closeConnection(connection, statement);
        }
        return rowsAffected;
    }

    @Override
    void updateUser(Driver driver) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(UPDATE_USER_QUERY);
            prepareUpdateUserStatement(driver, statement);
            statement.executeUpdate();
            licenseDAO.prepareUpdateDriverLicensesStatement(driver, connection);
            connection.commit();
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException("SQLException when updating driver.", e);
        } finally {
            closeConnection(connection, statement);
        }

    }*/
}
