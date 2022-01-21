package com.epam.volodko.dao;

import com.epam.volodko.dao.database.ConnectionPool;
import com.epam.volodko.dao.database.pool_exception.ConnectionPoolException;
import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public abstract class DataBaseIT {

    private JdbcTemplate jdbcTemplate;

    @Before
    public void initTestDB() throws SQLException, ClassNotFoundException {
        jdbcTemplate = TestConfigDB.getInstance().getScrollableJdbcTemplate();
        cleanDB();
    }

    @BeforeClass
    public static void initConnectionPool() throws ConnectionPoolException {
        ConnectionPool.init();
    }

    @AfterClass
    public static void disposeConnectionPool() throws ConnectionPoolException {
        ConnectionPool.getInstance().dispose();
    }

    public void cleanDB() throws SQLException {
        getJdbcTemplate().update(Query.DROP_DATABASE);
        getJdbcTemplate().update(Query.CREATE_DATABASE);
        getJdbcTemplate().update(Query.USE_DATABASE);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void fillDB(String... initQueryFiles) throws SQLException, IOException {
        for (String queryFile : initQueryFiles){
            jdbcTemplate.update(readSQLFile(Query.SQL_SCRIPTS_PATH + queryFile));
        }
    }

    private String readSQLFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null){
            builder.append(line);
        }
        return builder.toString();
    }
}
