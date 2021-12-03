package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.UserDAO;
import com.epam.volodko.dao.builder.BuilderFactory;
import com.epam.volodko.dao.database.ConnectionPool;
import com.epam.volodko.dao.database.pool_exception.ConnectionPoolException;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.builder.impl.UserBuilder;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.dao.table_name.Table;
import com.epam.volodko.entity.user.Role;
import com.epam.volodko.entity.user.User;

import java.sql.*;
import java.util.List;

public class UserDAOImpl extends AbstractDAOImpl<User> implements UserDAO {

    private static final String RETRIEVE_USER_BY_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=?;",
            Table.USERS, Column.USERS_ID);
    private static final String UPDATE_USER_QUERY = String.format(
            "UPDATE %s SET %s=?, %s=?, %s=?, %s=?, %s=? WHERE %s=?;",
            Table.USERS, Column.USERS_LOGIN, Column.USERS_PASSWORD, Column.USERS_NAME,
            Column.USERS_PHONE, Column.USERS_ROLE_ID, Column.USERS_ID);

    private final UserBuilder builder = BuilderFactory.getUserBuilder();

    public UserDAOImpl() {
        super(BuilderFactory.getUserBuilder(), Table.USERS, Column.USERS_ID);
    }

    @Override
    public User retrieveUserById(int userId) throws DAOException {
        User user;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(RETRIEVE_USER_BY_ID_QUERY);
            statement.setObject(1, userId);
            resultSet = statement.executeQuery();
            user = builder.build(resultSet);
        } catch (ConnectionPoolException e) {
            throw new DAOException("ConnectionPoolException in UserDAO.retrieveUserById()", e);
            //todo logger
        } catch (SQLException e) {
            throw new DAOException("SQLException in UserDAO.retrieveUserById()", e);
            //todo logger
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return user;
    }

    @Override
    public User retrieveUserByLogin(String userLogin) {
        return null;
    }

    @Override
    public List<User> retrieveAllUsers() {
        return null;
    }

    @Override
    public List<? extends User> retrieveUsersByRole(Role role) {
        return null;
    }

    @Override
    public void updateUser(User user) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(UPDATE_USER_QUERY);
            statement.setObject(1, user.getLogin());
            statement.setObject(1, user.getLogin());
            statement.setObject(1, user.getLogin());
            statement.setObject(1, user.getLogin());
            statement.setObject(1, user.getLogin());
            statement.setObject(1, user.getLogin());

            statement.executeUpdate();
        } catch (ConnectionPoolException e) {
            throw new DAOException("ConnectionPoolException in UserDAO.updateUser()", e);
            //todo logger
        } catch (SQLException e) {
            throw new DAOException("SQLException in UserDAO.updateUser()", e);
            //todo logger
        } finally {
            closeConnection(connection, statement);
        }
    }

    @Override
    public void saveNewUser(User user) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            String sqlQuery = String.format("INSERT INTO users (login, password, name, phone, role_id) " +
                    "VALUES ('%s', '%s', '%s', '%s', %d);", user.getLogin(), user.getPassword(), user.getName(),
                    user.getPhone(), user.getRole().getRole_id());
            statement = connection.prepareStatement(sqlQuery);
            statement.executeUpdate();
        } catch (ConnectionPoolException e) {
            throw new DAOException("ConnectionPoolException in UserDAO.saveNewUser()", e);
            //todo logger
        } catch (SQLException e) {
            throw new DAOException("SQLException in UserDAO.saveNewUser()", e);
            //todo logger
        } finally {
            closeConnection(connection, statement);
        }
    }

    @Override
    public void deleteUserById(int userId) {

    }

    @Override
    public void deleteUserByLogin(String userLogin) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            String sqlQuery = String.format("DELETE FROM users WHERE login='%s';", userLogin);
            statement = connection.prepareStatement(sqlQuery);
            statement.executeUpdate();
        } catch (ConnectionPoolException e) {
            throw new DAOException("ConnectionPoolException in UserDAO.deleteUserByLogin()", e);
            //todo logger
        } catch (SQLException e) {
            throw new DAOException("SQLException in UserDAO.deleteUserByLogin()", e);
            //todo logger
        } finally {
            closeConnection(connection, statement);
        }
    }

    private void closeConnection(Connection connection, Statement statement, ResultSet resultSet) throws DAOException {
        try {
            ConnectionPool.getInstance().closeConnection(connection, statement, resultSet);
        } catch (ConnectionPoolException e) {
            throw new DAOException("ConnectionPoolException while closing connection in UserDAO", e);
            //todo logger
        }
    }

    private void closeConnection(Connection connection, Statement statement) throws DAOException {
        try {
            ConnectionPool.getInstance().closeConnection(connection, statement);
        } catch (ConnectionPoolException e) {
            throw new DAOException("ConnectionPoolException while closing connection in UserDAO", e);
            //todo logger
        }
    }
}
