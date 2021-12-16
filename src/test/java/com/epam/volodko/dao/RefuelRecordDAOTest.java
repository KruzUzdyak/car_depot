package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.car.Car;
import com.epam.volodko.entity.car.RefuelRecord;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class RefuelRecordDAOTest {

    RefuelRecordDAO recordDAO = DAOFactory.getInstance().getRefuelRecordDAO();

    @Test
    public void testFindById() throws DAOException {
        int id = 1;
        RefuelRecord record = recordDAO.findById(id);
        System.out.println(record);

        assertNotNull(record);
        // TODO: 15.12.2021 make full test
    }

    @Ignore("Not final yet.")
    @Test
    public void testFindAll() throws DAOException {
        List<RefuelRecord> records = recordDAO.findAll();
        for (RefuelRecord record : records) {
            System.out.println(record);
        }

        assertFalse(records.isEmpty());
    }

    @Test
    public void testSaveNew() throws DAOException {
        Car car = new Car();
        car.setId(1);
        RefuelRecord record = new RefuelRecord(0, new Date(), 10, 51, car);
        int rowsAffected = recordDAO.saveNew(record);
        int expectedAffect = 1;
        assertEquals(expectedAffect, rowsAffected);
    }

    @Test
    public void testDelete() throws DAOException{
        RefuelRecord record = new RefuelRecord();
        record.setId(2);
        int rowsAffected = recordDAO.delete(record);
        int expectedAffect = 1;

        assertEquals(expectedAffect, rowsAffected);
    }

    @Test
    public void testUpdate() throws DAOException{
        RefuelRecord record = recordDAO.findById(1);
        record.setRefuelDate(new Date(1000L));
        record.setFuelPrice(100);
        record.setRefuelAmount(9000);
        Car car = new Car();
        car.setId(2);
        record.setCar(car);
        int rowsAffected = recordDAO.update(record);
        int expectedAffect = 1;

        assertEquals(expectedAffect, rowsAffected);
    }

}