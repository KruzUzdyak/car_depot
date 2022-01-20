package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.database.ConnectionPool;
import com.epam.volodko.dao.database.pool_exception.ConnectionPoolException;
import com.epam.volodko.dao.exception.DAOException;

import java.sql.*;

public abstract class AbstractDAO {

    int deleteById(int id, String query) throws DAOException {
        int rowsAffected;
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            rowsAffected = statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement);
        }
        return rowsAffected;
    }

    int processQuery(int firstId, int secondId, String query) throws DAOException {
        int rowsAffected;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, firstId);
            statement.setInt(2, secondId);
            rowsAffected = statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement);
        }
        return rowsAffected;
    }

    void closeConnection(Connection connection, Statement statement, ResultSet resultSet)
            throws DAOException {
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

    int getGeneratedKey(Statement statement) throws DAOException {
        ResultSet generatedKeys;
        int generatedKey;
        try {
            generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()){
                generatedKey = generatedKeys.getInt(1);
            } else
                throw new DAOException("Creating failed, no ID obtained.");
        } catch (SQLException e) {
            throw new DAOException("SQLException when try to get generated keys.", e);
        }
        try {
            generatedKeys.close();
        } catch (SQLException e) {
            throw new DAOException("SQLException when try to close generated keys result set.", e);
        }
        return generatedKey;
    }
}
