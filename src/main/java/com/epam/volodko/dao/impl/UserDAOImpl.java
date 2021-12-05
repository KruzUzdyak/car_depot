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

public class UserDAOImpl implements UserDAO {

    //todo дописать джоины
    private static final String FIND_USER_BY_ID_QUERY = String.format(
            "SELECT * FROM %s, %s WHERE %s.%s=? AND %s.%s=%s.%s;",
            Table.USERS, Table.ROLES, Table.USERS, Column.USERS_ID, Table.USERS,
            Column.USERS_ROLE_ID, Table.ROLES, Column.ROLES_ROLE_ID);
    private static final String FIND_ALL_USERS_QUERY = String.format(
            "SELECT * FROM %s, %s WHERE %s.%s=%s.%s;",
            Table.USERS, Table.ROLES, Table.USERS, Column.USERS_ROLE_ID,
            Table.ROLES, Column.ROLES_ROLE_ID);

    @Override
    public User findById(int userId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(FIND_USER_BY_ID_QUERY);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                user = BuilderFactory.getUserBuilder().build(resultSet);
            } else {
                user = new User();
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("ConnectionPoolException when try to find user by id.",  e);
        } catch (SQLException e) {
            throw new DAOException("SQLException when try to find user by id.", e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return user;
    }

    @Override
    public List<User> findAll() throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(FIND_ALL_USERS_QUERY);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                users.add(BuilderFactory.getUserBuilder().build(resultSet));
            }

        } catch (ConnectionPoolException e) {
            throw new DAOException("ConnectionPoolException when try to find all users.",  e);
        } catch (SQLException e) {
            throw new DAOException("SQLException when try to find all users.", e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return users;
    }

    @Override
    public List<User> findByRole(Role role) throws DAOException {
        return null;
    }

    private void closeConnection(Connection connection, PreparedStatement statement, ResultSet resultSet) throws DAOException {
        try {
            ConnectionPool.getInstance().closeConnection(connection, statement, resultSet);
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }
}
