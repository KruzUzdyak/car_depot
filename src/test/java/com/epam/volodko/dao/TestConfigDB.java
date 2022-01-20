package com.epam.volodko.dao;

import org.flywaydb.core.internal.jdbc.JdbcTemplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TestConfigDB {

    private static final TestConfigDB instance = new TestConfigDB();

    private final ResourceBundle bundle = ResourceBundle.getBundle("db");
    private final String driverName = bundle.getString("db.driver");
    private final String url = bundle.getString("db.url");
    private final String user = bundle.getString("db.user");
    private final String password = bundle.getString("db.password");

    private JdbcTemplate jdbcTemplate;

    private TestConfigDB(){}

    public JdbcTemplate getJdbcTemplate() throws SQLException, ClassNotFoundException {
        if (jdbcTemplate == null) {
            Class.forName(driverName);
            Connection connection = DriverManager.getConnection(url, user, password);
            jdbcTemplate = new JdbcTemplate(connection);
        }
        return jdbcTemplate;
    }

    public static TestConfigDB getInstance() {
        return instance;
    }
}
