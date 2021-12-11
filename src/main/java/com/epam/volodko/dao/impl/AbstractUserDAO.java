package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.database.ConnectionPool;
import com.epam.volodko.dao.database.pool_exception.ConnectionPoolException;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.dao.table_name.Table;
import com.epam.volodko.entity.user.User;

import java.sql.*;
import java.util.List;

public abstract class AbstractUserDAO<T extends User> {

    protected static final String SAVE_NEW_USER_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?);",
            Table.USERS, Column.USERS_LOGIN, Column.USERS_PASSWORD, Column.USERS_NAME,
            Column.USERS_PHONE, Column.USERS_ROLE_ID);
    protected static final String DELETE_USER_QUERY = String.format(
            "DELETE FROM %s WHERE %s = ?;",
            Table.USERS, Column.USERS_ID);
    protected static final String UPDATE_USER_QUERY = String.format(
            "UPDATE %s SET %s = ?, %s = ?, %s = ? WHERE %s = ?;",
            Table.USERS, Column.USERS_LOGIN, Column.USERS_NAME, Column.USERS_PHONE, Column.USERS_ID);
    protected static final String UPDATE_USER_PASSWORD_QUERY = String.format(
            "UPDATE %s SET %s = ? WHERE %s = ?;",
            Table.USERS, Column.USERS_PASSWORD, Column.USERS_ID);
    protected static final String GET_LAST_ADDED_USER_ID_QUERY = String.format(
            "SELECT %s FROM %s WHERE %s = ?;",
            Column.USERS_ID, Table.USERS, Column.USERS_LOGIN);

    abstract T findById(int userId) throws DAOException;

    abstract T findByLogin(String userLogin) throws DAOException;

    abstract List<T> findAll() throws DAOException;

    abstract int saveNewUser(T user) throws DAOException;

    abstract void updateUser(T user) throws DAOException;

    int getLastAddedUserId(T user, Connection connection, PreparedStatement statement) throws SQLException {
        statement = connection.prepareStatement(GET_LAST_ADDED_USER_ID_QUERY);
        statement.setString(1, user.getLogin());
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getInt(Column.USERS_ID);
    }

    void deleteUser(int userId) throws DAOException{
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(DELETE_USER_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException("SQLException while try to delete user.", e);
        } finally {
            closeConnection(connection, statement);
        }
    }

    void updateUserPassword(T user) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(UPDATE_USER_PASSWORD_QUERY);
            statement.setString(1, user.getPassword());
            statement.setInt(2, user.getUserId());
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException("SQLException while try to update user password.", e);
        } finally {
            closeConnection(connection, statement);
        }
    }

    void closeConnection(Connection connection, Statement statement, ResultSet resultSet) throws DAOException {
        try {
            ConnectionPool.getInstance().closeConnection(connection, statement, resultSet);
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }

    void closeConnection(Connection connection, Statement statement) throws DAOException{
        try {
            ConnectionPool.getInstance().closeConnection(connection, statement);
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }

    void prepareSaveUserStatement(T user, PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getLogin());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getName());
        statement.setString(4, user.getPhone());
        statement.setInt(5, user.getRole().getRoleId());
    }

    void prepareUpdateUserStatement(T user, PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getLogin());
        statement.setString(2, user.getName());
        statement.setString(3, user.getPhone());
        statement.setInt(4, user.getUserId());
    }

}
