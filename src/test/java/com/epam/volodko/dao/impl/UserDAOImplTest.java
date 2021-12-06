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
    public void checkFindByLogin() throws DAOException {
        String adminLogin = "admin1";
        User actualAdmin = userDAO.findByLogin(adminLogin);
        System.out.println(actualAdmin);
        System.out.println();

        String clientLogin = "client1";
        User actualClient = userDAO.findByLogin(clientLogin);
        System.out.println(actualClient);
        System.out.println();

        String driverLogin = "driver2";
        User actualDriver = userDAO.findByLogin(driverLogin);
        System.out.println(actualDriver);

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
    public void checkSaveNewUser() throws DAOException {
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

        System.out.println(admin);
        System.out.println(client);
        System.out.println(driver);
    }

    @Test
    public void checkDeleteUser() throws DAOException {
        Admin admin = new Admin(100, "testLogin2", "testPassword1", "testName1",
                "testPhone1", Role.ADMIN, new Date(), "testNote1");
        userDAO.saveNewUser(admin);

        Client client = new Client(100, "testLoginClient2", "testPasswordClient", "testNameClient",
                "testPhoneClient", Role.CLIENT, "JST IRAY", "China guys");
        userDAO.saveNewUser(client);

        Driver driver = new Driver(100, "driverLogin2", "driverPass", "driverName",
                "driverPhone", Role.DRIVER);
        driver.addLicense(new DriverLicense(DriverLicenseType.D, new Date(), "testLicenseNumber"));
        userDAO.saveNewUser(driver);

        User actualAdmin = userDAO.findByLogin(admin.getLogin());
        User actualClient = userDAO.findByLogin(client.getLogin());
        User actualDriver = userDAO.findByLogin(driver.getLogin());

        userDAO.deleteUser(actualAdmin);
        userDAO.deleteUser(actualClient);
        userDAO.deleteUser(actualDriver);

        //todo make full test.
    }

    @Test
    public void checkUpdateUser() throws DAOException{
        Admin admin = (Admin) userDAO.findUsersByRole(Role.ADMIN).get(0);
        admin.setPhone("!UpdatedTestPhone!");
        admin.setNote("!UpdatedTestNote!");
        userDAO.updateUser(admin);

        Client client = (Client) userDAO.findUsersByRole(Role.CLIENT).get(0);
        client.setName("UpdatedClientName");
        client.setCompany("UpdatedCompany");
        userDAO.updateUser(client);

        Driver driver = (Driver) userDAO.findUsersByRole(Role.DRIVER).get(0);
        DriverLicense license = new DriverLicense(DriverLicenseType.A1, new Date(1000L), "TEST_LICENSE");
        driver.addLicense(license);
        driver.setName("TEST_DRIVER_NAME");
        userDAO.updateUser(driver);

        //todo make full test.
    }
}