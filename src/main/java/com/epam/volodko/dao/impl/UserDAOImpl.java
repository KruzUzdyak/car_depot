package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.UserDAO;
import com.epam.volodko.dao.database.ConnectionPool;
import com.epam.volodko.dao.database.pool_exception.ConnectionPoolException;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.impl.builder.UserBuilder;
import com.epam.volodko.entity.user.Role;
import com.epam.volodko.entity.user.User;

import java.sql.*;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private UserBuilder builder = UserBuilder.getInstance();

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
            user = builder.buildUser(resultSet);
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
    public void updateUser(User user) {

    }

    @Override
    public void saveNewUser(User user) {

    }

    @Override
    public void deleteUserById(int userId) {

    }

    @Override
    public void deleteUserByLogin(int userLogin) {

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
