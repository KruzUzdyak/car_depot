package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.user.Driver;
import com.epam.volodko.entity.user.Role;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DriverDAOTest {

    UserDAO<Driver> driverDAO = DAOFactory.getInstance().getDriverDAO();

    @Test
    public void testFindById() throws DAOException{
        int driverId = 2;
        int otherId = 1;
        Driver driver = driverDAO.findById(driverId);
        Driver otherUser = driverDAO.findById(otherId);

        assertNotNull(driver);
        assertNull(otherUser);
    }

    @Test
    public void testFindByLogin() throws DAOException{
        String driverLogin = "driver1";
        String otherLogin = "admin1";
        Driver driver = driverDAO.findByLogin(driverLogin);
        Driver otherUser  = driverDAO.findByLogin(otherLogin);

        assertNotNull(driver);
        assertNull(otherUser);
    }

    @Test
    public void testFindAll() throws DAOException{
        List<Driver> drivers = driverDAO.findAll();

        for (Driver driver : drivers){
            assertNotNull(driver);
            assertEquals(Role.DRIVER, driver.getRole());
        }
    }


}