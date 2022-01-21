package com.epam.volodko.dao;

import org.flywaydb.core.api.FlywayException;
import org.flywaydb.core.internal.database.base.DatabaseType;
import org.flywaydb.core.internal.jdbc.JdbcNullTypes;
import org.flywaydb.core.internal.jdbc.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScrollableJdbcTemplate extends JdbcTemplate {

    public ScrollableJdbcTemplate(Connection connection) {
        super(connection);
    }

    public ScrollableJdbcTemplate(Connection connection, DatabaseType databaseType){
        super(connection, databaseType);
    }

    @Override
    protected PreparedStatement prepareStatement(String sql, Object[] params) throws SQLException{
        PreparedStatement statement = this.connection.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        for(int i = 0; i < params.length; ++i) {
            if (params[i] == null) {
                statement.setNull(i + 1, this.nullType);
            } else if (params[i] instanceof Integer) {
                statement.setInt(i + 1, (Integer)params[i]);
            } else if (params[i] instanceof Boolean) {
                statement.setBoolean(i + 1, (Boolean)params[i]);
            } else if (params[i] instanceof String) {
                statement.setString(i + 1, params[i].toString());
            } else if (params[i] == JdbcNullTypes.StringNull) {
                statement.setNull(i + 1, this.nullType);
            } else if (params[i] == JdbcNullTypes.IntegerNull) {
                statement.setNull(i + 1, this.nullType);
            } else {
                if (params[i] != JdbcNullTypes.BooleanNull) {
                    throw new FlywayException("Unhandled object of type '" + params[i].getClass().getName() + "'. Please contact support or leave an issue on GitHub.");
                }

                statement.setNull(i + 1, this.nullType);
            }
        }

        return statement;
    }
}
