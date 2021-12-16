package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.car.Car;
import com.epam.volodko.entity.car.RepairRecord;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class RepairRecordDAOTest {

    private final RepairRecordDAO recordDAO = DAOFactory.getInstance().getRepairRecordDAO();

    @Test
    public void testFindById() throws DAOException{
        int repairId = 1;
        RepairRecord record = recordDAO.findById(repairId);
        System.out.println(record);

        assertNotNull(record);
    }

    @Test
    public void testFindAll() throws DAOException{
        List<RepairRecord> records = recordDAO.findAll();
        for (RepairRecord record : records){
            System.out.println(record);
        }

        assertFalse(records.isEmpty());
    }

    @Test
    public void testSaveNew() throws DAOException{
        Car car = new Car();
        car.setId(1);
        RepairRecord record = new RepairRecord(0, new Date(10102220292L), new Date(11102220292L), 9000, car);
        int rowsAffected = recordDAO.saveNew(record);
        int expectedAffect = 1;

        assertEquals(expectedAffect, rowsAffected);
    }

    @Test
    public void testDeleteByID() throws DAOException{
        int recordId = 2;
        int rowsAffected = recordDAO.deleteById(recordId);
        int expectedAffect = 1;

        assertEquals(expectedAffect, rowsAffected);
    }

    @Test
    public void testUpdate() throws DAOException{
        Car car = new Car();
        car.setId(1);
        RepairRecord record = new RepairRecord(1, new Date(10102220292L), new Date(11102220292L), 9000, car);
        int rowsAffected = recordDAO.update(record);
        int expectedAffect = 1;

        assertEquals(expectedAffect, rowsAffected);
    }

}