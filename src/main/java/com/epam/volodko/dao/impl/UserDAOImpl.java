package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.UserDAO;
import com.epam.volodko.dao.builder.BuilderFactory;
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
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends AbstractUserDAO<User> implements UserDAO<User> {

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

    @Override
    public User findById(int userId) throws DAOException {
        User user = null;
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
        User user = null;
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
    public int saveInfo(User user) throws DAOException {
        throw new UnsupportedOperationException("Use concrete classes like AdminDAO, ClientDAO, DriverDAO for this operation");
    }

    @Override
    public int updateInfo(User user) throws DAOException {
        throw new UnsupportedOperationException("Use concrete classes like AdminDAO, ClientDAO, DriverDAO for this operation");
    }
}
