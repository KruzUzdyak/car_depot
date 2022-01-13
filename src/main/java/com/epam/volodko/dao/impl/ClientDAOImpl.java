package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.UserDAO;
import com.epam.volodko.dao.builder.BuilderFactory;
import com.epam.volodko.dao.database.ConnectionPool;
import com.epam.volodko.dao.database.pool_exception.ConnectionPoolException;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.dao.table_name.Table;
import com.epam.volodko.entity.user.Client;
import com.epam.volodko.entity.user.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOImpl extends AbstractUserDAO<Client> implements UserDAO<Client> {

    private static final String FIND_CLIENT_BY_ID_QUERY = String.format(
            "SELECT * FROM %s AS u JOIN %s AS r ON u.%s = r.%s LEFT JOIN %s ci ON u.%s = ci.%s WHERE u.%s = ?;",
            Table.USERS, Table.ROLES, Column.USERS_ROLE_ID, Column.ROLES_ID, Table.CLIENT_INFO,
            Column.USERS_ID, Column.CLIENT_INFO_USER_ID, Column.USERS_ID);
    private static final String FIND_CLIENT_BY_LOGIN_QUERY = String.format(
            "SELECT * FROM %s AS u JOIN %s AS r ON u.%s = r.%s LEFT JOIN %s ci ON u.%s = ci.%s WHERE u.%s = ?;",
            Table.USERS, Table.ROLES, Column.USERS_ROLE_ID, Column.ROLES_ID, Table.CLIENT_INFO,
            Column.USERS_ID, Column.CLIENT_INFO_USER_ID, Column.USERS_LOGIN);
    private static final String FIND_ALL_CLIENTS_QUERY = String.format(
            "SELECT * FROM %s AS u JOIN %s AS r ON u.%s = r.%s LEFT JOIN %s ci ON u.%s = ci.%s WHERE r.%s = ?;",
            Table.USERS, Table.ROLES, Column.USERS_ROLE_ID, Column.ROLES_ID, Table.CLIENT_INFO,
            Column.USERS_ID, Column.CLIENT_INFO_USER_ID, Column.ROLES_ID);
    private static final String SAVE_NEW_CLIENT_INFO_QUERY = String.format(
            "INSERT INTO %s (%s) VALUES (?);",
            Table.CLIENT_INFO, Column.CLIENT_INFO_USER_ID);
    private static final String UPDATE_CLIENT_INFO_QUERY = String.format(
            "UPDATE %s SET %s = ?, %s = ? WHERE %s = ?;",
            Table.CLIENT_INFO, Column.CLIENT_INFO_COMPANY, Column.CLIENT_INFO_NOTE, Column.CLIENT_INFO_USER_ID);

    @Override
    public Client findById(int userId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Client client = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(FIND_CLIENT_BY_ID_QUERY);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                client = BuilderFactory.getClientBuilder().build(resultSet);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return client;
    }

    @Override
    public Client findByLogin(String userLogin) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Client client = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(FIND_CLIENT_BY_LOGIN_QUERY);
            statement.setString(1, userLogin);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                client = BuilderFactory.getClientBuilder().build(resultSet);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return client;
    }

    @Override
    public List<Client> findAll() throws DAOException {
        List<Client> clients = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(FIND_ALL_CLIENTS_QUERY);
            statement.setInt(1, Role.CLIENT.getRoleId());
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                clients.add(BuilderFactory.getClientBuilder().build(resultSet));
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return clients;
    }

    @Override
    public int saveInfo(int userId) throws DAOException {
        return saveInfo(userId, SAVE_NEW_CLIENT_INFO_QUERY);
    }


    @Override
    public int updateInfo(Client client) throws DAOException {
        int rowsAffected;
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(UPDATE_CLIENT_INFO_QUERY);
            statement.setString(1, client.getCompany());
            statement.setString(2, client.getNote());
            statement.setInt(3, client.getId());
            rowsAffected = statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement);
        }
        return rowsAffected;
    }
}
