package com.epam.volodko.dao;

import com.epam.volodko.dao.database.ConnectionPool;
import com.epam.volodko.dao.database.pool_exception.ConnectionPoolException;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.entity.car.Car;
import com.epam.volodko.entity.car.CarModel;
import com.epam.volodko.entity.car.CarType;
import com.epam.volodko.entity.user.*;
import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public abstract class DataBaseIT {

    private static JdbcTemplate jdbcTemplate;

    @BeforeClass
    public static void initConnectionPool() throws ConnectionPoolException, SQLException, ClassNotFoundException {
        jdbcTemplate = TestConfigDB.getInstance().getScrollableJdbcTemplate();
        cleanDB();
        ConnectionPool.init();
    }

    @AfterClass
    public static void disposeConnectionPool() throws ConnectionPoolException {
        ConnectionPool.getInstance().dispose();
    }

    public static void cleanDB() throws SQLException {
        getJdbcTemplate().update(Query.DROP_DATABASE);
        getJdbcTemplate().update(Query.CREATE_DATABASE);
        getJdbcTemplate().update(Query.USE_DATABASE);
    }

    public static JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public static void fillDB(String... initQueryFiles) throws SQLException, IOException {
        for (String queryFile : initQueryFiles){
            jdbcTemplate.update(readSQLFile(Query.SQL_SCRIPTS_PATH + queryFile));
        }
    }

    private static String readSQLFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null){
            builder.append(line);
        }
        return builder.toString();
    }

    protected CarType createCarType(ResultSet rs) throws SQLException {
        int id = rs.getInt(Column.CAR_TYPES_ID);
        String typeName = rs.getString(Column.CAR_TYPES_NAME);
        DriverLicenseType licenseType = LicenseTypeProvider
                .getLicenseType(rs.getInt(Column.CAR_TYPES_REQUIRED_LICENSE_ID));
        return new CarType(id, typeName, licenseType);
    }

    protected CarModel createCarModel(ResultSet rs) throws SQLException {
        int id = rs.getInt(Column.CAR_MODELS_ID);
        String modelName = rs.getString(Column.CAR_MODELS_NAME);
        int capacity = rs.getInt(Column.CAR_MODELS_CAPACITY);
        int fuelTank = rs.getInt(Column.CAR_MODELS_FUEL_TANK);
        String loadType = rs.getString(Column.CAR_MODELS_LOAD_TYPE);
        CarType carType = createCarType(rs);
        return new CarModel(id, modelName, capacity, fuelTank, loadType, carType);
    }

    protected Driver createDriver(ResultSet rs) throws SQLException {
        int id = rs.getInt(Column.USERS_ID);
        String login = rs.getString(Column.USERS_LOGIN);
        String name = rs.getString(Column.USERS_NAME);
        String phone = rs.getString(Column.USERS_PHONE);
        Role role = RoleProvider.getRole(rs.getInt(Column.USERS_ROLE_ID));
        Driver driver = new Driver(id, login, null, name, phone, role);
        addLicensesToDriver(rs, driver);
        return driver;
    }


    private void addLicensesToDriver(ResultSet rs, Driver driver) throws SQLException {
        driver.addLicense(createDriverLicense(rs));
        int currentDriverId = driver.getId();
        while (rs.next()) {
            int nextDriverId = rs.getInt(Column.USERS_ID);
            if (currentDriverId == nextDriverId) {
                driver.addLicense(createDriverLicense(rs));
            } else {
                rs.previous();
                break;
            }
        }
    }

    protected DriverLicense createDriverLicense(ResultSet rs) throws SQLException {
        DriverLicenseType licenseType = LicenseTypeProvider
                .getLicenseType(rs.getInt("dl." + Column.DRIVER_LICENSES_LICENSE_ID));
        Date obtainingDate = null;
        if (licenseType != null){
            obtainingDate =  new Date(rs.getLong(Column.DRIVER_LICENSES_OBTAINING_DATE));
        }
        String licenseNumber = rs.getString(Column.DRIVER_LICENSES_LICENSE_NUMBER);
        return new DriverLicense(licenseType, obtainingDate, licenseNumber);
    }

    protected Car createCar(java.sql.ResultSet rs) throws SQLException {
        int id = rs.getInt(Column.CARS_ID);
        String plateNumber = rs.getString(Column.CARS_PLATE_NUMBER);
        int fuelLevel = rs.getInt(Column.CARS_FUEL_LEVEL);
        int mileage = rs.getInt(Column.CARS_MILEAGE);
        boolean broken = rs.getBoolean(Column.CARS_BROKEN);
        CarModel model = createCarModel(rs);
        Driver driver = null;
        if (rs.getInt(Column.CARS_DRIVER_ID) != 0) {
            driver = createDriver(rs);
        }
        return new Car(id, plateNumber, fuelLevel, mileage, broken, model, driver);
    }
}
