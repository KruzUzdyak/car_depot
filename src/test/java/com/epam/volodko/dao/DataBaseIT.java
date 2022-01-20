package com.epam.volodko.dao;

import com.epam.volodko.dao.database.ConnectionPool;
import com.epam.volodko.dao.database.pool_exception.ConnectionPoolException;
import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import org.junit.Before;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public abstract class DataBaseIT {

    private JdbcTemplate jdbcTemplate;

    @Before
    public void initTestDB() throws SQLException, ClassNotFoundException {
        jdbcTemplate = TestConfigDB.getInstance().getJdbcTemplate();
        cleanDB();
    }

    @Before
    public void initConnectionPool() throws ConnectionPoolException {
        ConnectionPool.init();
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


//    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
//        DataBaseIT test = new DriverLicenseDaoIT();
//        test.initJdbcTemplate();
//        test.cleanDB();
//        test.fillDB(SQL_CREATE_LICENSE_TYPES,
//                SQL_CREATE_CAR_TYPES,
//                SQL_CREATE_CAR_MODELS,
//                SQL_CREATE_ROLES,
//                SQL_CREATE_USERS,
//                SQL_CREATE_CARS,
//                SQL_CREATE_DRIVER_LICENSES,
//                SQL_CREATE_ORDERS,
//                SQL_CREATE_REPAIR_RECORDS,
//                SQL_CREATE_CLIENT_INFO,
//                SQL_CREATE_ADMIN_INFO,
//                SQL_CREATE_REFUEL_RECORDS);
//        test.fillDB(SQL_FILL_LICENSE_TYPES,
//                SQL_FILL_CAR_TYPES,
//                SQL_FILL_CAR_MODELS,
//                SQL_FILL_ROLES,
//                SQL_FILL_USERS,
//                SQL_FILL_CARS,
//                SQL_FILL_DRIVER_LICENSES,
//                SQL_FILL_ORDERS,
//                SQL_FILL_REPAIR_RECORDS,
//                SQL_FILL_CLIENT_INFO,
//                SQL_FILL_ADMIN_INFO,
//                SQL_FILL_REFUEL_RECORDS);
//    }
}
