package com.epam.volodko.dao.query_operator;

import com.epam.volodko.dao.builder.Builder;
import com.epam.volodko.dao.database.ConnectionPool;
import com.epam.volodko.dao.database.pool_exception.ConnectionPoolException;
import com.epam.volodko.dao.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueryOperator<T> {

    private final Builder<T> builder;

    public QueryOperator(Builder<T> builder) {
        this.builder = builder;
    }

    public void setStatementParams(PreparedStatement statement, Object... params) throws SQLException {
        for (int i=0; i<= params.length; i++){
            statement.setObject(i+1, params[i]);
        }
    }

    public List<T> executeQuery(String query, Object... params) throws DAOException{
        List<T> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(query);
            setStatementParams(statement, params);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                T entity = builder.build(resultSet);
                result.add(entity);
            }
        } catch (SQLException e) {
            throw new DAOException("SQLException while executing select query.",e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("ConnectionPoolException while executing select query.", e);
        } finally {
            try {
                ConnectionPool.getInstance().closeConnection(connection, statement, resultSet);
            } catch (ConnectionPoolException e) {
                throw new DAOException("ConnectionPoolException while closing connection.", e);
            }
        }
        return result;
    }

    public T executeSingleEntityQuery(String query, Object... params) throws DAOException{
        List<T> result = executeQuery(query, params);
        if (result.size() > 0){
            return result.get(0);
        } else {
            return null;
        }
    }

    public int executeUpdate(String query, Object... params) throws DAOException{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;
        int rowsAffected;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            setStatementParams(statement, params);
            rowsAffected = statement.executeUpdate();
            generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys != null && generatedKeys.next()){
                rowsAffected = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException("SQLException while executing update query.",e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("ConnectionPoolException while executing update query.", e);
        } finally {
            try {
                ConnectionPool.getInstance().closeConnection(connection, statement, generatedKeys);
            } catch (ConnectionPoolException e) {
                throw new DAOException("ConnectionPoolException while closing connection.", e);
            }
        }
        return rowsAffected;
    }
}
