package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.DAOFactory;
import com.epam.volodko.dao.UserDAO;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.user.*;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserDAOImplTest {

    UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

    @Test
    public void checkFindById() throws DAOException {
        int userId = 1;
        User actualUser = userDAO.findById(userId);
        System.out.println(actualUser);
        System.out.println();

        userId = 3;
        actualUser = userDAO.findById(userId);
        System.out.println(actualUser);
        System.out.println();

        userId = 4;
        actualUser = userDAO.findById(userId);
        System.out.println(actualUser);

        //todo make full test.
    }

    @Test
    public void checkFindAll() throws DAOException {
        List<User> actualUsers = userDAO.findAll();
        actualUsers.forEach(System.out::println);

        //todo make full test.
    }

    @Test
    public void checkFindByRole() throws DAOException {
        List<User> actualUsers = userDAO.findUsersByRole(Role.ADMIN);
        actualUsers.forEach(System.out::println);
        System.out.println();

        actualUsers = userDAO.findUsersByRole(Role.CLIENT);
        actualUsers.forEach(System.out::println);
        System.out.println();

        actualUsers = userDAO.findUsersByRole(Role.DRIVER);
        actualUsers.forEach(System.out::println);

        //todo make full test.
    }

    @Test
    public void checkSaveNewUser() throws DAOException{
        Admin admin = new Admin(100, "testLogin1", "testPassword1", "testName1",
                "testPhone1", Role.ADMIN, new Date(), "testNote1");
        userDAO.saveNewUser(admin);

        Client client = new Client(100, "testLoginClient", "testPasswordClient", "testNameClient",
                "testPhoneClient", Role.CLIENT, "JST IRAY", "China guys");
        userDAO.saveNewUser(client);

        Driver driver = new Driver(100, "driverLogin", "driverPass", "driverName",
                "driverPhone", Role.DRIVER);
        driver.addLicense(new DriverLicense(DriverLicenseType.D, new Date(), "testLicenseNumber"));
        userDAO.saveNewUser(driver);
    }

    @Test
    public void checkDeleteUser() throws DAOException{
        Admin admin = new Admin(100, "testLogin1", "testPassword1", "testName1",
                "testPhone1", Role.ADMIN, new Date(), "testNote1");
        userDAO.saveNewUser(admin);

        Client client = new Client(100, "testLoginClient", "testPasswordClient", "testNameClient",
                "testPhoneClient", Role.CLIENT, "JST IRAY", "China guys");
        userDAO.saveNewUser(client);

        Driver driver = new Driver(100, "driverLogin", "driverPass", "driverName",
                "driverPhone", Role.DRIVER);
        driver.addLicense(new DriverLicense(DriverLicenseType.D, new Date(), "testLicenseNumber"));
        userDAO.saveNewUser(driver);

        userDAO.deleteUser(admin);
        userDAO.deleteUser(client);
        userDAO.deleteUser(driver);
    }
}