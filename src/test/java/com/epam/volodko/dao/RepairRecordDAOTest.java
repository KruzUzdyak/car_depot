package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.car.RepairRecord;
import org.junit.Test;

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

}