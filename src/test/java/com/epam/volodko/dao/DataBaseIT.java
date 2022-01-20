package com.epam.volodko.dao;

import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import org.junit.Before;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public /*abstract*/ class DataBaseIT {

    private JdbcTemplate jdbcTemplate;

    @Before
    public void initJdbcTemplate() throws SQLException, ClassNotFoundException {
        jdbcTemplate = TestConfigDB.getInstance().getJdbcTemplate();
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void cleanDB() throws SQLException {
        getJdbcTemplate().update(Query.DROP_DATABASE);
        getJdbcTemplate().update(Query.CREATE_DATABASE);
        getJdbcTemplate().update(Query.USE_DATABASE);
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


    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        DataBaseIT test = new DataBaseIT();
        test.initJdbcTemplate();
        test.cleanDB();
        test.fillDB(Query.SQL_CREATE_LICENSE_TYPES, Query.SQL_FILL_LICENSE_TYPES);

    }
}
