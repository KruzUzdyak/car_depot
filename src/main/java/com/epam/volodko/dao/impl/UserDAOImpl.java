package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.UserDAO;
import com.epam.volodko.dao.builder.BuilderFactory;
import com.epam.volodko.dao.database.ConnectionPool;
import com.epam.volodko.dao.database.pool_exception.ConnectionPoolException;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.dao.table_name.Table;
import com.epam.volodko.entity.user.Role;
import com.epam.volodko.entity.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends AbstractDAO implements UserDAO {

    private static final String FIND_PASSWORD_BY_LOGIN_QUERY = String.format(
            "SELECT %s FROM %s WHERE %s=?;",
            Column.USERS_PASSWORD, Table.USERS, Column.USERS_LOGIN);
    private static final String FIND_USER_BY_ID_QUERY = String.format(
            "SELECT * FROM %s AS u JOIN %s AS r ON u.%s=r.%s WHERE u.%s=?;",
            Table.USERS, Table.ROLES,
            Column.USERS_ROLE_ID, Column.ROLES_ID, Column.USERS_ID);
    private static final String FIND_USER_BY_LOGIN_QUERY = String.format(
            "SELECT * FROM %s AS u JOIN %s AS r ON u.%s=r.%s WHERE u.%s=?;",
            Table.USERS, Table.ROLES,
            Column.USERS_ROLE_ID, Column.ROLES_ID, Column.USERS_LOGIN);
    private static final String FIND_ALL_USERS_QUERY = String.format(
            "SELECT * FROM %s AS u JOIN %s AS r ON u.%s=r.%s;",
            Table.USERS, Table.ROLES,
            Column.USERS_ROLE_ID, Column.ROLES_ID);
    private static final String FIND_USER_BY_ROLE_QUERY = String.format(
            "SELECT * FROM %s AS u JOIN %s AS r ON u.%s=r.%s WHERE u.%s=?;",
            Table.USERS, Table.ROLES,
            Column.USERS_ROLE_ID, Column.ROLES_ID, Column.USERS_ROLE_ID);
    private static final String SAVE_NEW_USER_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?);",
            Table.USERS, Column.USERS_LOGIN, Column.USERS_PASSWORD, Column.USERS_NAME,
            Column.USERS_PHONE, Column.USERS_ROLE_ID);
    private static final String DELETE_USER_QUERY = String.format(
            "DELETE FROM %s WHERE %s = ?;",
            Table.USERS, Column.USERS_ID);
    private static final String UPDATE_USER_QUERY = String.format(
            "UPDATE %s SET %s = ?, %s = ? WHERE %s = ?;",
            Table.USERS, Column.USERS_NAME, Column.USERS_PHONE, Column.USERS_ID);

    @Override
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

    @Override
    public User findById(int userId) throws DAOException {
        User user;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(FIND_USER_BY_ID_QUERY);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = BuilderFactory.getUserBuilder().build(resultSet);
            } else {
                throw new DAOException("Can't find user by id.");
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return user;
    }

    @Override
    public User findByLogin(String login) throws DAOException {
        User user;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(FIND_USER_BY_LOGIN_QUERY);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = BuilderFactory.getUserBuilder().build(resultSet);
            } else {
                throw new DAOException("Can't find user by login.");
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return user;
    }

    @Override
    public List<User> findAll() throws DAOException {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(FIND_ALL_USERS_QUERY);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(BuilderFactory.getUserBuilder().build(resultSet));
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return users;
    }

    @Override
    public List<User> findByRole(Role role) throws DAOException {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(FIND_USER_BY_ROLE_QUERY);
            statement.setInt(1, role.getRoleId());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(BuilderFactory.getUserBuilder().build(resultSet));
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return users;
    }

    @Override
    public int saveNew(User user) throws DAOException {
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
            user.setUserId(getGeneratedKey(statement));
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement);
        }
        return rowsAffected;
    }

    @Override
    public int deleteUser(User user) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        int rowsAffected;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(DELETE_USER_QUERY);
            statement.setInt(1, user.getId());
            rowsAffected = statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement);
        }
        return rowsAffected;
    }

    @Override
    public int updateNameAndPhone(User user) throws DAOException {
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

    @Override
    public int updateLogin(String login) throws DAOException {
        return 0;
    }

    @Override
    public int updateUserPassword(String password) throws DAOException {
        return 0;
    }
}
