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

    void closeConnection(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {

        }
    }

    void closeStatement(Statement statement, ResultSet resultSet){
        try {
            resultSet.close();
        } catch (SQLException e) {
            //todo logger.log(Level.ERROR, "ResultSet isn't closed.");
        }
        try{
            statement.close();
        } catch (SQLException e) {
            //todo logger.log(Level.ERROR, "Statement isn't closed.");
        }
    }

    void closeStatement(Statement statement){
        try{
            statement.close();
        } catch (SQLException e) {
            //todo logger.log(Level.ERROR, "Statement isn't closed.");
        }
    }
}
