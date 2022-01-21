package com.epam.volodko.dao;

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

    private ScrollableJdbcTemplate jdbcScrollableJdbcTemplate;

    private TestConfigDB(){}

    public ScrollableJdbcTemplate getScrollableJdbcTemplate() throws SQLException, ClassNotFoundException {
        if (jdbcScrollableJdbcTemplate == null) {
            Class.forName(driverName);
            Connection connection = DriverManager.getConnection(url, user, password);
            jdbcScrollableJdbcTemplate = new ScrollableJdbcTemplate(connection);
        }
        return jdbcScrollableJdbcTemplate;
    }

    public static TestConfigDB getInstance() {
        return instance;
    }
}
