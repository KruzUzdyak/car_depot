package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.AbstractDAO;
import com.epam.volodko.dao.builder.Builder;
import com.epam.volodko.dao.query_operator.QueryOperator;

public abstract class AbstractDAOImpl<T> implements AbstractDAO<T> {

    private static final String SELECT_ALL_QUERY = "SELECT * FROM ";
    private static final String RETRIEVE_BY_ID_QUERY = "SELECT * FROM ";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM ";


    protected final QueryOperator queryOperator;
    protected final Builder<T> builder;
    private final String tableName;
    private final String idName;

    protected AbstractDAOImpl(Builder<T> builder, String tableName, String idName) {
        this.queryOperator = new QueryOperator();
        this.builder = builder;
        this.tableName = tableName;
        this.idName = idName;
    }
}
