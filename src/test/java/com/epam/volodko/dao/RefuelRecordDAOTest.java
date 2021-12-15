package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.car.RefuelRecord;
import org.junit.Test;

import static org.junit.Assert.*;

public class RefuelRecordDAOTest {

    RefuelRecordDAO recordDAO = DAOFactory.getInstance().getRefuelRecordDAO();

    @Test
    public void testFindById() throws DAOException{
        int id = 1;
        RefuelRecord record = recordDAO.findById(id);
        System.out.println(record);

        assertNotNull(record);
        // TODO: 15.12.2021 make full test
    }

}