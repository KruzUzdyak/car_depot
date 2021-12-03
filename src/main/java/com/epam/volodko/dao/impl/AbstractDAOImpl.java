package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.AbstractDAO;
import com.epam.volodko.dao.builder.Builder;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.query_operator.QueryOperator;

import java.util.List;

public abstract class AbstractDAOImpl<T> implements AbstractDAO<T> {

    private static final String RETRIEVE_BY_ID_QUERY = "SELECT * FROM ";
    private static final String RETRIEVE_ALL_QUERY = "SELECT * FROM ";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM ";


    protected final QueryOperator<T> queryOperator;
    protected final Builder<T> builder;
    private final String tableName;
    private final String idName;

    protected AbstractDAOImpl(Builder<T> builder, String tableName, String idName) {
        this.queryOperator = new QueryOperator<>(builder);
        this.builder = builder;
        this.tableName = tableName;
        this.idName = idName;
    }

    @Override
    public T retrieveById(int id) throws DAOException {
        String query = String.format("%s %s WHERE %s=?;", RETRIEVE_BY_ID_QUERY, tableName, idName);
        return queryOperator.executeSingleEntityQuery(query);
    }

    @Override
    public List<T> retrieveAll() throws DAOException {
        String query = String.format("%s %s;", RETRIEVE_ALL_QUERY, tableName);
        return queryOperator.executeQuery(query);
    }

    @Override
    public int deleteByID(int id) throws DAOException {
        String query = String.format("%s %s WHERE %s=;", DELETE_BY_ID_QUERY, tableName, idName);
        return queryOperator.executeUpdate(query, id);
    }
}
