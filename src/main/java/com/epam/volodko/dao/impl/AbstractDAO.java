package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.database.ConnectionPool;
import com.epam.volodko.dao.database.pool_exception.ConnectionPoolException;
import com.epam.volodko.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractDAO {

    void closeConnection(Connection connection, Statement statement, ResultSet resultSet) throws DAOException {
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

    void rollback(Connection connection) throws DAOException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DAOException("SQLException when try to rollback changes.", e);
        }
    }
}
