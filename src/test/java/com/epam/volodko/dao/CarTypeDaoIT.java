package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.dao.table_name.Table;
import com.epam.volodko.entity.car.CarType;
import com.epam.volodko.entity.user.DriverLicenseType;
import com.epam.volodko.entity.user.LicenseTypeProvider;
import org.flywaydb.core.internal.jdbc.RowMapper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Theories.class)
public class CarTypeDaoIT extends DataBaseIT{

    private static final String FIND_CAR_TYPE_BY_ID_QUERY = String.format(
            "SELECT * FROM %s AS ct JOIN %s AS lt ON ct.%s = lt.%s WHERE ct.%s = ?;",
            Table.CAR_TYPES, Table.LICENSE_TYPES,
            Column.CAR_TYPES_REQUIRED_LICENSE_ID, Column.LICENSE_ID, Column.CAR_TYPES_ID);
    private static final String FIND_ALL_CAR_TYPES_QUERY = String.format(
            "SELECT * FROM %s AS ct JOIN %s AS lt ON ct.%s = lt.%s;",
            Table.CAR_TYPES, Table.LICENSE_TYPES,
            Column.CAR_TYPES_REQUIRED_LICENSE_ID, Column.LICENSE_ID);

    @DataPoints
    public static int[] carTypesId = new int[] {1, 2, 3, 4};

    private final CarTypeDAO carTypeDAO = DAOFactory.getInstance().getCarTypeDAO();

    @BeforeClass
    public static void initTables() throws IOException, SQLException {
        fillDB(Query.SQL_CREATE_LICENSE_TYPES, Query.SQL_CREATE_CAR_TYPES);
        fillDB(Query.SQL_FILL_LICENSE_TYPES, Query.SQL_FILL_CAR_TYPES);
    }

    @Theory
    public void testFindById(int id) throws DAOException, SQLException{
        CarType expected = getJdbcTemplate()
                .query(FIND_CAR_TYPE_BY_ID_QUERY, carTypeMapper(), id)
                .get(0);
        CarType actual = carTypeDAO.findById(id);

        assertEquals(expected, actual);
    }

    @Theory
    public void testDeleteById(int id) throws DAOException, SQLException, IOException {
        int rowsAffected = carTypeDAO.deleteById(id);
        List<CarType> result = getJdbcTemplate()
                .query(FIND_CAR_TYPE_BY_ID_QUERY, carTypeMapper(), id);

        assertEquals(1, rowsAffected);
        assertTrue(result.isEmpty());

        cleanDB();
        initTables();
    }

    @Test
    public void testFindAll() throws DAOException, SQLException{
        List<CarType> expected = getJdbcTemplate()
                .query(FIND_ALL_CAR_TYPES_QUERY, carTypeMapper());
        List<CarType> actual = carTypeDAO.findAll();

        assertEquals(expected, actual);
    }

    @Test
    public void testSaveNew() throws DAOException, SQLException{
        CarType expected = new CarType(-1, "test Type", DriverLicenseType.A1);
        int rowsAffected = carTypeDAO.saveNew(expected);
        CarType actual = getJdbcTemplate()
                .query(FIND_CAR_TYPE_BY_ID_QUERY, carTypeMapper(), expected.getCarTypeId())
                .get(0);

        assertEquals(1, rowsAffected);
        assertEquals(expected, actual);
    }

    @Test(expected = DAOException.class)
    public void testSaveNewWithWrongData() throws DAOException{
        CarType expected = new CarType(-1, null, DriverLicenseType.A1);
        carTypeDAO.saveNew(expected);
    }

    @Test
    public void testUpdate() throws DAOException, SQLException, IOException {
        CarType expected = getJdbcTemplate()
                .query(FIND_CAR_TYPE_BY_ID_QUERY, carTypeMapper(), 1)
                .get(0);
        expected.setTypeName("test type name");
        expected.setRequiredLicense(DriverLicenseType.I);

        int rowsAffected = carTypeDAO.update(expected);
        CarType actual = getJdbcTemplate()
                .query(FIND_CAR_TYPE_BY_ID_QUERY, carTypeMapper(), expected.getCarTypeId())
                .get(0);

        assertEquals(1, rowsAffected);
        assertEquals(expected, actual);

        cleanDB();
        initTables();
    }

    @Test(expected = DAOException.class)
    public void testUpdateWithWrongData() throws DAOException, SQLException {
        CarType carType = getJdbcTemplate()
                .query(FIND_CAR_TYPE_BY_ID_QUERY, carTypeMapper(), 1)
                .get(0);
        carType.setTypeName(null);
        carTypeDAO.saveNew(carType);
    }

    private RowMapper<CarType> carTypeMapper(){
        return this::createCarType;
    }


}
