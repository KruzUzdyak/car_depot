package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.dao.table_name.Table;
import com.epam.volodko.entity.car.Car;
import com.epam.volodko.entity.car.CarModel;
import com.epam.volodko.entity.car.CarType;
import com.epam.volodko.entity.user.Driver;
import com.epam.volodko.entity.user.DriverLicenseType;
import org.flywaydb.core.internal.jdbc.RowMapper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Theories.class)
public class CarDaoIT extends DataBaseIT {

    private static final String FIND_CAR_BY_ID_QUERY = String.format("""
                    SELECT * FROM %s c
                    JOIN %s cm on c.%s = cm.%s
                    JOIN %s ct on cm.%s = ct.%s
                    JOIN %s lt on ct.%s = lt.%s
                    LEFT JOIN %s u on c.%s = u.%s
                    LEFT JOIN  %s r on u.%s = r.%s
                    LEFT JOIN %s dl on u.%s = dl.%s
                    WHERE c.%s = ?;""",
            Table.CARS,
            Table.CAR_MODELS, Column.CARS_MODEL_ID, Column.CAR_MODELS_ID,
            Table.CAR_TYPES, Column.CAR_MODELS_TYPE_ID, Column.CAR_TYPES_ID,
            Table.LICENSE_TYPES, Column.CAR_TYPES_REQUIRED_LICENSE_ID, Column.LICENSE_ID,
            Table.USERS, Column.CARS_DRIVER_ID, Column.USERS_ID,
            Table.ROLES, Column.USERS_ROLE_ID, Column.ROLES_ID,
            Table.DRIVER_LICENSES, Column.USERS_ID, Column.DRIVER_LICENSES_USER_ID,
            Column.CARS_ID);
    private static final String FIND_CAR_BY_DRIVER_ID_QUERY = String.format("""
                    SELECT * FROM %s c
                    JOIN %s cm on c.%s = cm.%s
                    JOIN %s ct on cm.%s = ct.%s
                    JOIN %s lt on ct.%s = lt.%s
                    LEFT JOIN %s u on c.%s = u.%s
                    LEFT JOIN  %s r on u.%s = r.%s
                    LEFT JOIN %s dl on u.%s = dl.%s
                    WHERE c.%s = ?
                    ORDER BY dl.%s;""",
            Table.CARS,
            Table.CAR_MODELS, Column.CARS_MODEL_ID, Column.CAR_MODELS_ID,
            Table.CAR_TYPES, Column.CAR_MODELS_TYPE_ID, Column.CAR_TYPES_ID,
            Table.LICENSE_TYPES, Column.CAR_TYPES_REQUIRED_LICENSE_ID, Column.LICENSE_ID,
            Table.USERS, Column.CARS_DRIVER_ID, Column.USERS_ID,
            Table.ROLES, Column.USERS_ROLE_ID, Column.ROLES_ID,
            Table.DRIVER_LICENSES, Column.USERS_ID, Column.DRIVER_LICENSES_USER_ID,
            Column.CARS_DRIVER_ID, Column.DRIVER_LICENSES_LICENSE_ID);
    private static final String FIND_ALL_CARS_QUERY = String.format("""
                    SELECT * FROM %s c
                    JOIN %s cm on c.%s = cm.%s
                    JOIN %s ct on cm.%s = ct.%s
                    JOIN %s lt on ct.%s = lt.%s
                    LEFT JOIN %s u on c.%s = u.%s
                    LEFT JOIN  %s r on u.%s = r.%s
                    LEFT JOIN %s dl on u.%s = dl.%s;""",
            Table.CARS,
            Table.CAR_MODELS, Column.CARS_MODEL_ID, Column.CAR_MODELS_ID,
            Table.CAR_TYPES, Column.CAR_MODELS_TYPE_ID, Column.CAR_TYPES_ID,
            Table.LICENSE_TYPES, Column.CAR_TYPES_REQUIRED_LICENSE_ID, Column.LICENSE_ID,
            Table.USERS, Column.CARS_DRIVER_ID, Column.USERS_ID,
            Table.ROLES, Column.USERS_ROLE_ID, Column.ROLES_ID,
            Table.DRIVER_LICENSES, Column.USERS_ID, Column.DRIVER_LICENSES_USER_ID);

    @DataPoints("carsId")
    public static int[] carsId = new int[]{1, 2, 3, 4};
    @DataPoints("driversId")
    public static int[] driversId = new int[]{5, 6};

    private final CarDAO carDAO = DAOFactory.getInstance().getCarDAO();

    @BeforeClass
    public static void initTables() throws IOException, SQLException {
        fillDB(Query.SQL_CREATE_LICENSE_TYPES, Query.SQL_CREATE_CAR_TYPES, Query.SQL_CREATE_CAR_MODELS,
                Query.SQL_CREATE_ROLES, Query.SQL_CREATE_USERS, Query.SQL_CREATE_DRIVER_LICENSES,
                Query.SQL_CREATE_CARS);
        fillDB(Query.SQL_FILL_LICENSE_TYPES, Query.SQL_FILL_CAR_TYPES, Query.SQL_FILL_CAR_MODELS,
                Query.SQL_FILL_ROLES, Query.SQL_FILL_USERS, Query.SQL_FILL_DRIVER_LICENSES,
                Query.SQL_FILL_CARS);
    }

    @Theory
    public void testFindById(@FromDataPoints("carsId") int carId)
            throws SQLException, DAOException {
        Car actual = carDAO.findById(carId);
        Car expected = getJdbcTemplate()
                .query(FIND_CAR_BY_ID_QUERY, carMapper(), carId)
                .get(0);

        assertEquals(expected, actual);
    }

    @Theory
    public void testFindByDriverId(@FromDataPoints("driversId") int driverId)
            throws SQLException, DAOException {
        Car actual = carDAO.findByDriver(driverId);
        Car expected = getJdbcTemplate()
                .query(FIND_CAR_BY_DRIVER_ID_QUERY, carMapper(), driverId)
                .get(0);

        assertEquals(expected, actual);
    }

    @Theory
    public void testDeleteById(@FromDataPoints("carsId") int carId)
            throws DAOException, SQLException, IOException {
        int rowsAffected = carDAO.deleteById(carId);
        List<Car> result = getJdbcTemplate()
                .query(FIND_CAR_BY_ID_QUERY, carMapper(), carId);

        assertEquals(1, rowsAffected);
        assertTrue(result.isEmpty());

        cleanDB();
        initTables();
    }

    @Test
    public void testFindAll() throws DAOException, SQLException {
        List<Car> expected = getJdbcTemplate()
                .query(FIND_ALL_CARS_QUERY, carMapper());
        List<Car> actual = carDAO.findAll();

        assertEquals(expected, actual);
    }

    @Test
    public void testSaveNew() throws DAOException, SQLException{
        CarType carType = new CarType(1, "small bus", DriverLicenseType.B);
        CarModel carModel = new CarModel(1, "Volkswagen Transporter T4", 8,
                70, "people", carType);
        Car expected = new Car(-1, "plate number", 102, 1222,
                false, carModel, null);
        int rowsAffected = carDAO.saveNew(expected);
        Car actual = getJdbcTemplate()
                .query(FIND_CAR_BY_ID_QUERY, carMapper(), expected.getId())
                .get(0);

        assertEquals(1, rowsAffected);
        assertEquals(expected, actual);
    }

    @Test(expected = DAOException.class)
    public void testSaveWithWrongData() throws DAOException{
        CarType carType = new CarType(1, "small bus", DriverLicenseType.B);
        CarModel carModel = new CarModel(1, "Volkswagen Transporter T4", 8,
                70, "people", carType);
        Car car = new Car(-1, null, 102, 1222,
                false, carModel, null);
        carDAO.saveNew(car);
    }

    @Test
    public void testUpdate() throws DAOException, SQLException{
        Car expected = getJdbcTemplate()
                .query(FIND_CAR_BY_ID_QUERY, carMapper(), 1)
                .get(0);
        expected.setPlateNumber("new plate number");
        expected.setBroken(!expected.isBroken());
        expected.setFuelLevel(10000);
        expected.setMileage(100000000);
        CarType carType = new CarType(2, "bus", DriverLicenseType.D);
        CarModel carModel = new CarModel(2, "MAZ 232", 35,
                300, "people", carType);
        expected.setModel(carModel);

        int rowsAffected = carDAO.update(expected);
        Car actual = getJdbcTemplate()
                .query(FIND_CAR_BY_ID_QUERY, carMapper(), expected.getId())
                .get(0);

        assertEquals(1, rowsAffected);
        assertEquals(expected, actual);
    }

    @Test(expected = DAOException.class)
    public void testUpdateWithWrongData() throws DAOException, SQLException {
        Car car = getJdbcTemplate()
                .query(FIND_CAR_BY_ID_QUERY, carMapper(), 1)
                .get(0);
        car.setPlateNumber(null);
        carDAO.update(car);
    }

    private RowMapper<Car> carMapper() {
        return (rs) -> {
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
        };
    }
}