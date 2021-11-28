package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.UserDAO;
import com.epam.volodko.dao.database.ConnectionPoolFactory;
import com.epam.volodko.dao.database.pool_exception.ConnectionPoolException;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.impl.builders.UserBuilder;
import com.epam.volodko.entity.users.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private final UserBuilder builder = new UserBuilder();

    public UserDAOImpl() {
    }

    @Override
    public User retrieveUserById(int userId) throws DAOException {
        User user;
        try {
            Connection con = ConnectionPoolFactory.getConnectionPool().takeConnection();
            Statement st = con.createStatement();
            String sqlQuery = String.format("SELECT * FROM USERS WHERE user_id = %d;", userId);
            ResultSet resultSet = st.executeQuery(sqlQuery);
            user = builder.buildUser(resultSet);
        } catch (ConnectionPoolException e) {
            throw new DAOException("ConnectionException in UserDAOImpl.retrieveUserById()", e);
        } catch (SQLException e) {
            throw new DAOException("SQLException in UserDAOImpl.retrieveUserById()", e);
        } finally {
            //TODO
        }
        return user;
    }

    @Override
    public User retrieveUserByName(String name) throws DAOException {
        User user;
        try {
            Connection con = ConnectionPoolFactory.getConnectionPool().takeConnection();
            Statement st = con.createStatement();
            String sqlQuery = String.format("SELECT * FROM USERS WHERE name = '%s';", name);
            ResultSet resultSet = st.executeQuery(sqlQuery);
            user = builder.buildUser(resultSet);
        } catch (ConnectionPoolException e) {
            throw new DAOException("ConnectionException in UserDAOImpl.retrieveUserByName()", e);
        } catch (SQLException e) {
            throw new DAOException("SQLException in UserDAOImpl.retrieveUserByName()", e);
        } finally {
            //TODO
        }
        return user;
    }

    @Override
    public List<User> retrieveAllUsers() {
        //TODO
        return new ArrayList<>();
    }

    @Override
    public List<User> retrieveAllUsers(Role role) throws DAOException {
        return new ArrayList<>();
    }

    @Override
    public void saveUser(User user) throws DAOException {
        try {
            Connection con = ConnectionPoolFactory.getConnectionPool().takeConnection();
            Statement statement = con.createStatement();
            String sqlQuery = String.format("INSERT INTO users (login, password, name, phone, role_id)\n" +
                            "VALUES ('%s', '%s', '%s', '%s', %d);", user.getLogin(), user.getPassword(),
                    user.getName(), user.getPhone(), user.getRole().getRole_id());
            statement.executeUpdate(sqlQuery);
        } catch (ConnectionPoolException e) {
            throw new DAOException("ConnectionException in UserDAOImpl.saveUser()", e);
        } catch (SQLException e) {
            throw new DAOException("SQLException in UserDAOImpl.saveUser()", e);
        } finally {
            //TODO
        }
    }

    @Override
    public void saveUser(List<User> users) throws DAOException {
        for (User user : users) {
            saveUser(user);
        }
    }

    @Override
    public void deleteUser(User user) throws DAOException {

    }

    @Override
    public void deleteAllUsers() throws DAOException {

    }

    @Override
    public void deleteUserById(int userId) throws DAOException {

    }

    @Override
    public void deleteUserByName(String name) throws DAOException {

    }
}
