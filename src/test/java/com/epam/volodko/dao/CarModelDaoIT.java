package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.dao.table_name.Table;
import com.epam.volodko.entity.car.CarModel;
import com.epam.volodko.entity.car.CarType;
import com.epam.volodko.entity.user.DriverLicenseType;
import org.flywaydb.core.internal.jdbc.RowMapper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Theories.class)
public class CarModelDaoIT extends DataBaseIT {

    private static final String GET_CAR_MODEL_BY_ID_QUERY = String.format(
            "SELECT * FROM %s AS cm JOIN %s AS ct ON cm.%s = ct.%s JOIN %s AS lt ON ct.%s = lt.%s WHERE %s = ?;",
            Table.CAR_MODELS, Table.CAR_TYPES, Column.CAR_MODELS_TYPE_ID, Column.CAR_TYPES_ID,
            Table.LICENSE_TYPES, Column.CAR_TYPES_REQUIRED_LICENSE_ID, Column.LICENSE_ID, Column.CAR_MODELS_ID);
    private static final String GET_ALL_CAR_MODELS_QUERY = String.format(
            "SELECT * FROM %s AS cm JOIN %s AS ct ON cm.%s = ct.%s JOIN %s AS lt ON ct.%s = lt.%s;",
            Table.CAR_MODELS, Table.CAR_TYPES, Column.CAR_MODELS_TYPE_ID, Column.CAR_TYPES_ID,
            Table.LICENSE_TYPES, Column.CAR_TYPES_REQUIRED_LICENSE_ID, Column.LICENSE_ID);
    private static final String GET_CAR_MODELS_BY_CAR_TYPE_QUERY = String.format(
            "SELECT * FROM %s AS cm JOIN %s AS ct ON cm.%s = ct.%s JOIN %s AS lt ON ct.%s = lt.%s WHERE %s = ?;",
            Table.CAR_MODELS, Table.CAR_TYPES, Column.CAR_MODELS_TYPE_ID, Column.CAR_TYPES_ID,
            Table.LICENSE_TYPES, Column.CAR_TYPES_REQUIRED_LICENSE_ID, Column.LICENSE_ID ,Column.CAR_MODELS_TYPE_ID);

    @DataPoints
    public static int[] carTypesId = new int[]{1, 2, 3, 4};
    @DataPoints
    public static CarType[] carTypes = new CarType[]{
            new CarType(1, null, null),
            new CarType(2, null, null),
            new CarType(3, null, null),
            new CarType(4, null, null),
    };

    private final CarModelDAO modelDAO = DAOFactory.getInstance().getCarModelDAO();

    @BeforeClass
    public static void initTables() throws IOException, SQLException {
        fillDB(Query.SQL_CREATE_LICENSE_TYPES, Query.SQL_CREATE_CAR_TYPES, Query.SQL_CREATE_CAR_MODELS);
        fillDB(Query.SQL_FILL_LICENSE_TYPES, Query.SQL_FILL_CAR_TYPES, Query.SQL_FILL_CAR_MODELS);
    }

    @Theory
    public void testFindById(int id) throws SQLException, DAOException{
        CarModel expected = getJdbcTemplate()
                .query(GET_CAR_MODEL_BY_ID_QUERY, carModelMapper(), id)
                .get(0);
        CarModel actual = modelDAO.findById(id);

        assertEquals(expected, actual);
    }

    @Theory
    public void testFindByCarType(CarType carType) throws SQLException, DAOException{
        List<CarModel> expected = getJdbcTemplate()
                .query(GET_CAR_MODELS_BY_CAR_TYPE_QUERY, carModelMapper(), carType.getCarTypeId());
        List<CarModel> actual = modelDAO.findByCarType(carType);

        assertEquals(expected, actual);
    }

    @Theory
    public void testDeleteById(int id) throws DAOException, SQLException, IOException{
        int rowsAffected= modelDAO.deleteById(id);
        List<CarModel> result = getJdbcTemplate()
                .query(GET_CAR_MODEL_BY_ID_QUERY, carModelMapper(), id);

        assertEquals(1, rowsAffected);
        assertTrue(result.isEmpty());

        cleanDB();
        initTables();
    }

    @Test
    public void testFindAll() throws SQLException, DAOException{
        List<CarModel> expected = getJdbcTemplate()
                .query(GET_ALL_CAR_MODELS_QUERY, carModelMapper());
        List<CarModel> actual = modelDAO.findAll();

        assertEquals(expected, actual);
    }

    @Test
    public void testSaveNew() throws DAOException, SQLException{
        CarType carType = new CarType(1, "small bus", DriverLicenseType.B);
        CarModel expected = new CarModel(-1, "test name", 1000, 1000, "test load",
                carType);
        int rowsAffected = modelDAO.saveNew(expected);
        CarModel actual = getJdbcTemplate()
                .query(GET_CAR_MODEL_BY_ID_QUERY, carModelMapper(), expected.getId())
                .get(0);

        assertEquals(1, rowsAffected);
        assertEquals(expected, actual);
    }

    @Test(expected = DAOException.class)
    public void testSaveNewWithWrongData() throws DAOException {
        CarType carType = new CarType(1, "small bus", DriverLicenseType.B);
        CarModel carModel = new CarModel(-1, null, 0, 0, "stub",
                carType);
        modelDAO.saveNew(carModel);
    }

    @Test
    public void testUpdate() throws DAOException, SQLException, IOException {
        CarModel expected = getJdbcTemplate()
                .query(GET_CAR_MODEL_BY_ID_QUERY, carModelMapper(), 1)
                .get(0);
        expected.setModelName("new name");
        expected.setCapacity(9000);
        expected.setLoadType("new load");
        expected.setFuelTank(10);
        expected.setType(new CarType(4, "big truck with trailer", DriverLicenseType.CE));

        int rowsAffected = modelDAO.update(expected);
        CarModel actual = getJdbcTemplate()
                .query(GET_CAR_MODEL_BY_ID_QUERY, carModelMapper(), expected.getId())
                .get(0);

        assertEquals(1, rowsAffected);
        assertEquals(expected, actual);

        cleanDB();
        initTables();
    }

    @Test(expected = DAOException.class)
    public void testUpdateWithWrongData() throws DAOException, SQLException{
        CarModel model = getJdbcTemplate()
                .query(GET_CAR_MODEL_BY_ID_QUERY, carModelMapper(), 1)
                .get(0);
        model.setModelName(null);
        modelDAO.update(model);
    }

    private RowMapper<CarModel> carModelMapper() {
        return this::createCarModel;
    }


}

