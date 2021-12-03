package com.epam.volodko.dao.query_operator;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QueryOperator {

    public void setStatementParams(PreparedStatement statement, Object... params) throws SQLException {
        for (int i=0; i<= params.length; i++){
            statement.setObject(i+1, params[i]);
        }
    }
}
