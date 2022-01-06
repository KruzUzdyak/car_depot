package com.epam.volodko.dao;

import com.epam.volodko.dao.database.ConnectionPool;
import com.epam.volodko.dao.database.pool_exception.ConnectionPoolException;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.user.Role;
import com.epam.volodko.entity.user.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserDAOTest {

    UserDAO<User> userDAO = DAOFactory.getInstance().getUserDAO();

    @BeforeClass
    public static void init() throws ConnectionPoolException {
        ConnectionPool.init();
    }

    @Test
    public void testFindById() throws DAOException {
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
    public void testFindByLogin() throws DAOException {
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
    public void testFindAll() throws DAOException {
        List<User> actualUsers = userDAO.findAll();
        actualUsers.forEach(System.out::println);

        //todo make full test.
    }

    @Test
    public void testSaveNewUser() throws DAOException {
        User admin = new User(-1, "testSaveLogin", "testSavePassword",
                "testSaveName", "testSavePhone", Role.ADMIN);
        int rowsAffected = userDAO.saveNew(admin);
        int expectedAffect = 1;

        assertEquals(expectedAffect, rowsAffected);
        assertTrue(admin.getId() > 0);

    }

    @Test
    public void testDeleteUser() throws DAOException {
        int userId = 13;
        int rowsAffected = userDAO.deleteById(userId);
        int expectedAffect = 1;

        assertEquals(expectedAffect, rowsAffected);
    }

    @Test
    public void testUpdateUser() throws DAOException{
        User user = userDAO.findById(4);
        user.setName("testUpdateName");
        user.setPhone("testUpdatePhone");

        int rowsAffected = userDAO.update(user);
        int expectedAffect = 1;
        User actualUser = userDAO.findById(4);

        assertEquals(expectedAffect, rowsAffected);
        assertEquals(user, actualUser);
    }

    @Test
    public void testUpdateLogin() throws DAOException{
        int userId = 1;
        User user = userDAO.findById(userId);
        String login = "testUpdateLogin";

        int rowsAffected = userDAO.updateLogin(1, login);
        int expectedAffect = 1;
        User actualUser = userDAO.findByLogin(login);

        assertEquals(expectedAffect, rowsAffected);
        assertEquals(user, actualUser);
    }

    @Test
    public void testUpdatePassword() throws DAOException{
        int userId = 1;
        String password = "testUpdatePassword";
        User user = userDAO.findById(userId);

        int rowsAffected = userDAO.updatePassword(userId, password);
        int expectedAffect = 1;
        String actualPassword = userDAO.findPasswordHashByLogin(user.getLogin());

        assertEquals(expectedAffect, rowsAffected);
        assertEquals(password, actualPassword);
    }

}