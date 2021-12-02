package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.UserDAO;
import com.epam.volodko.dao.builder.BuilderFactory;
import com.epam.volodko.dao.database.ConnectionPool;
import com.epam.volodko.dao.database.pool_exception.ConnectionPoolException;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.builder.UserBuilder;
import com.epam.volodko.entity.user.Role;
import com.epam.volodko.entity.user.User;

import java.sql.*;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private final UserBuilder builder = BuilderFactory.getUserBuilder();

    public UserDAOImpl() {
    }

    @Override
    public int retrieveUserCount() throws DAOException {
        Connection connection = null;
        CallableStatement statement = null;
        int userCount;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareCall("{call getUserCount(?)}");
            statement.registerOutParameter("userCount", Types.INTEGER);
            statement.execute();
            userCount = statement.getInt("userCount");
        } catch (ConnectionPoolException e) {
            throw new DAOException("ConnectionPoolException in UserDAO.retrieveUserCount()", e);
            //todo logger
        } catch (SQLException e) {
            throw new DAOException("SQLException in UserDAO.retrieveUserCount()", e);
            //todo logger
        } finally {
            closeConnection(connection, statement);
        }
        return userCount;

    }

    @Override
    public User retrieveUserById(int userId) throws DAOException {
        User user;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement("SELECT * FROM users WHERE user_id=?;");
            statement.setInt(1, userId);
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
            String sqlQuery = String.format("UPDATE users SET login = '%s', password = '%s', " +
                    "name = '%s', phone = '%s', role_id = %d WHERE user_id = %d;", user.getLogin(), user.getPassword(),
                    user.getName(), user.getPhone(), user.getRole().getRole_id(), user.getUserId());
            statement = connection.prepareStatement(sqlQuery);
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
