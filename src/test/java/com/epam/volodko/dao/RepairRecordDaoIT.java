package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.dao.table_name.Table;
import com.epam.volodko.entity.car.*;
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
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Theories.class)
public class RepairRecordDaoIT extends DataBaseIT{

    private static final String FIND_REPAIR_RECORD_BY_ID_QUERY = String.format("""
                    SELECT * FROM %s rep
                    JOIN %s c on c.%s = rep.%s
                    JOIN %s cm on c.%s = cm.%s
                    JOIN %s ct on cm.%s = ct.%s
                    JOIN %s lt on ct.%s = lt.%s
                    LEFT JOIN %s u on c.%s = u.%s
                    LEFT JOIN  %s r on u.%s = r.%s
                    LEFT JOIN %s dl on u.%s = dl.%s
                    WHERE rep.%s = ?;""",
            Table.REPAIR_RECORDS,
            Table.CARS, Column.CARS_ID, Column.REFUEL_RECORDS_CAR_ID,
            Table.CAR_MODELS, Column.CARS_MODEL_ID, Column.CAR_MODELS_ID,
            Table.CAR_TYPES, Column.CAR_MODELS_TYPE_ID, Column.CAR_TYPES_ID,
            Table.LICENSE_TYPES, Column.CAR_TYPES_REQUIRED_LICENSE_ID, Column.LICENSE_ID,
            Table.USERS, Column.CARS_DRIVER_ID, Column.USERS_ID,
            Table.ROLES, Column.USERS_ROLE_ID, Column.ROLES_ID,
            Table.DRIVER_LICENSES, Column.USERS_ID, Column.DRIVER_LICENSES_USER_ID,
            Column.REPAIR_RECORDS_ID);
    private static final String FIND_ALL_REPAIR_RECORD_QUERY = String.format("""
                    SELECT * FROM %s rep
                    JOIN %s c on c.%s = rep.%s
                    JOIN %s cm on c.%s = cm.%s
                    JOIN %s ct on cm.%s = ct.%s
                    JOIN %s lt on ct.%s = lt.%s
                    LEFT JOIN %s u on c.%s = u.%s
                    LEFT JOIN  %s r on u.%s = r.%s
                    LEFT JOIN %s dl on u.%s = dl.%s;""",
            Table.REPAIR_RECORDS,
            Table.CARS, Column.CARS_ID, Column.REFUEL_RECORDS_CAR_ID,
            Table.CAR_MODELS, Column.CARS_MODEL_ID, Column.CAR_MODELS_ID,
            Table.CAR_TYPES, Column.CAR_MODELS_TYPE_ID, Column.CAR_TYPES_ID,
            Table.LICENSE_TYPES, Column.CAR_TYPES_REQUIRED_LICENSE_ID, Column.LICENSE_ID,
            Table.USERS, Column.CARS_DRIVER_ID, Column.USERS_ID,
            Table.ROLES, Column.USERS_ROLE_ID, Column.ROLES_ID,
            Table.DRIVER_LICENSES, Column.USERS_ID, Column.DRIVER_LICENSES_USER_ID);

    @DataPoints
    public static int[] recordsId = new int[] {1, 2, 3, 4};

    private final RepairRecordDAO recordDAO = DAOFactory.getInstance().getRepairRecordDAO();

    @BeforeClass
    public static void initTables() throws IOException, SQLException {
        fillDB(Query.SQL_CREATE_LICENSE_TYPES, Query.SQL_CREATE_CAR_TYPES, Query.SQL_CREATE_CAR_MODELS,
                Query.SQL_CREATE_ROLES, Query.SQL_CREATE_USERS, Query.SQL_CREATE_DRIVER_LICENSES,
                Query.SQL_CREATE_CARS, Query.SQL_CREATE_REPAIR_RECORDS);
        fillDB(Query.SQL_FILL_LICENSE_TYPES, Query.SQL_FILL_CAR_TYPES, Query.SQL_FILL_CAR_MODELS,
                Query.SQL_FILL_ROLES, Query.SQL_FILL_USERS, Query.SQL_FILL_DRIVER_LICENSES,
                Query.SQL_FILL_CARS, Query.SQL_FILL_REPAIR_RECORDS);
    }

    @Theory
    public void testFindById(int id) throws DAOException, SQLException{
        RepairRecord expected = getJdbcTemplate()
                .query(FIND_REPAIR_RECORD_BY_ID_QUERY, refuelRecordMapper(), id)
                .get(0);
        RepairRecord actual = recordDAO.findById(id);

        assertEquals(expected, actual);
    }

    @Theory
    public void testDeleteById(int id) throws DAOException, SQLException, IOException {
        int rowsAffected = recordDAO.deleteById(id);
        List<RepairRecord> result = getJdbcTemplate()
                .query(FIND_REPAIR_RECORD_BY_ID_QUERY, refuelRecordMapper(), id);

        assertEquals(1, rowsAffected);
        assertTrue(result.isEmpty());

        cleanDB();
        initTables();
    }

    @Test
    public void testFindAll() throws DAOException, SQLException{
        List<RepairRecord> expected = getJdbcTemplate()
                .query(FIND_ALL_REPAIR_RECORD_QUERY, refuelRecordMapper());
        List<RepairRecord> actual = recordDAO.findAll();

        assertEquals(expected, actual);
    }

    @Test
    public void testSaveNew() throws DAOException, SQLException{
        CarType carType = new CarType(1, "small bus", DriverLicenseType.B);
        CarModel carModel = new CarModel(1, "Volkswagen Transporter T4", 8,
                70, "people", carType);
        Car car = new Car(1, "6795MM-5", 50, 12000,
                true, carModel, null);
        RepairRecord expected = new RepairRecord(-1, new Date(10000000000L), new Date(100000000000L), 10, car);

        int rowsAffected = recordDAO.saveNew(expected);
        RepairRecord actual = getJdbcTemplate()
                .query(FIND_REPAIR_RECORD_BY_ID_QUERY, refuelRecordMapper(), expected.getId())
                .get(0);

        assertEquals(1, rowsAffected);
        assertEquals(expected, actual);
    }

    @Test
    public void testUpdate() throws DAOException, SQLException {
        RepairRecord expected = getJdbcTemplate()
                .query(FIND_REPAIR_RECORD_BY_ID_QUERY, refuelRecordMapper(), 1)
                .get(0);
        expected.setExpenses(9000);
        expected.setRepairStart(new Date(99999999999L));
        expected.setRepairEnd(new Date(9999999999999L));

        int rowsAffected = recordDAO.update(expected);
        RepairRecord actual = getJdbcTemplate()
                .query(FIND_REPAIR_RECORD_BY_ID_QUERY, refuelRecordMapper(), expected.getId())
                .get(0);

        assertEquals(1, rowsAffected);
        assertEquals(expected, actual);
    }

    private RowMapper<RepairRecord> refuelRecordMapper(){
        return (rs) -> {
            int id = rs.getInt(Column.REPAIR_RECORDS_ID);
            Date repairStart = new Date(rs.getLong(Column.REPAIR_RECORDS_REPAIR_START));
            Date repairEnd = new Date(rs.getLong(Column.REPAIR_RECORDS_REPAIR_END));
            int expenses = rs.getInt(Column.REPAIR_RECORDS_EXPENSES);
            Car car = createCar(rs);
            return new RepairRecord(id, repairStart, repairEnd, expenses, car);
        };
    }
}