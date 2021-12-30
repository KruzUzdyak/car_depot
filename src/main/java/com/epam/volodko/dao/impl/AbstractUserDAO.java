package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.database.ConnectionPool;
import com.epam.volodko.dao.database.pool_exception.ConnectionPoolException;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.dao.table_name.Table;
import com.epam.volodko.entity.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractUserDAO<T extends User> extends AbstractDAO{

    private static final String FIND_PASSWORD_BY_LOGIN_QUERY = String.format(
            "SELECT %s FROM %s WHERE %s=?;",
            Column.USERS_PASSWORD, Table.USERS, Column.USERS_LOGIN);
    private static final String SAVE_NEW_USER_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?);",
            Table.USERS, Column.USERS_LOGIN, Column.USERS_PASSWORD, Column.USERS_NAME,
            Column.USERS_PHONE, Column.USERS_ROLE_ID);
    private static final String DELETE_USER_QUERY = String.format(
            "DELETE FROM %s WHERE %s = ?;",
            Table.USERS, Column.USERS_ID);
    private static final String UPDATE_LOGIN_QUERY = String.format(
            "UPDATE %s SET %s = ? WHERE %s = ?;",
            Table.USERS, Column.USERS_LOGIN, Column.USERS_ID);
    private static final String UPDATE_PASSWORD_QUERY = String.format(
            "UPDATE %s SET %s = ? WHERE %s = ?;",
            Table.USERS, Column.USERS_PASSWORD, Column.USERS_ID);
    private static final String UPDATE_USER_QUERY = String.format(
            "UPDATE %s SET %s = ?, %s = ? WHERE %s = ?;",
            Table.USERS, Column.USERS_NAME, Column.USERS_PHONE, Column.USERS_ID);

    public String findPasswordByLogin(String login) throws DAOException {
        String password = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(FIND_PASSWORD_BY_LOGIN_QUERY);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                password = resultSet.getString(Column.USERS_PASSWORD);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return password;
    }

    public int saveNew(T user) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        int rowsAffected;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SAVE_NEW_USER_QUERY);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getPhone());
            statement.setInt(5, user.getRole().getRoleId());
            rowsAffected = statement.executeUpdate();
            user.setId(getGeneratedKey(statement));
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement);
        }
        return rowsAffected;
    }

    public int deleteById(int id) throws DAOException {
        return deleteById(id, DELETE_USER_QUERY);
    }

    public int update(T user) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;;
        int rowsAffected;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(1, user.getName());
            statement.setString(2, user.getPhone());
            statement.setInt(3, user.getId());
            rowsAffected = statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement);
        }
        return rowsAffected;
    }

    public int updateLogin(int userId, String newLogin) throws DAOException {
        return processUpdatingUserFieldById(userId, newLogin, UPDATE_LOGIN_QUERY);
    }

    public int updatePassword(int userId, String newPassword) throws DAOException {
        return processUpdatingUserFieldById(userId, newPassword, UPDATE_PASSWORD_QUERY);
    }

    private int processUpdatingUserFieldById(int userId, String newPassword, String updatePasswordQuery)
            throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        int rowsAffected;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(updatePasswordQuery);
            statement.setString(1, newPassword);
            statement.setInt(2, userId);
            rowsAffected = statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement);
        }
        return rowsAffected;
    }

    public int saveInfo(T user) throws DAOException {
        throw new UnsupportedOperationException("Use AdminDAO or ClientDAO for this operation.");
    }

    public int updateInfo(T user) throws DAOException {
        throw new UnsupportedOperationException("Use AdminDAO or ClientDAO for this operation.");
    }
}
