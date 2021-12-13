package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.user.Role;
import com.epam.volodko.entity.user.User;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserDAOTest {

    UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

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
    public void testFindByRole() throws DAOException {
        List<User> actualUsers = userDAO.findByRole(Role.ADMIN);
        actualUsers.forEach(System.out::println);
        System.out.println();

        actualUsers = userDAO.findByRole(Role.CLIENT);
        actualUsers.forEach(System.out::println);
        System.out.println();

        actualUsers = userDAO.findByRole(Role.DRIVER);
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
        User user = new User(5, "admin2", null, null, null, null);
        int rowsAffected = userDAO.deleteUser(user);
        int expectedAffect = 1;

        assertEquals(expectedAffect, rowsAffected);
    }

    @Test
    public void testUpdateUser() throws DAOException{
        User user = userDAO.findById(4);
        user.setName("testUpdateName");
        user.setPhone("testUpdatePhone");

        int rowsAffected = userDAO.updateNameAndPhone(user);
        int expectedAffect = 1;
        User actualUser = userDAO.findById(4);

        assertEquals(expectedAffect, rowsAffected);
        assertEquals(user, actualUser);
    }

    @Test
    public void testUpdateLogin() throws DAOException{
        User user = userDAO.findById(1);
        String login = "testUpdateLogin";
        user.setLogin(login);

        int rowsAffected = userDAO.updateLogin(user);
        int expectedAffect = 1;
        User actualUser = userDAO.findByLogin(login);

        assertEquals(expectedAffect, rowsAffected);
        assertEquals(user, actualUser);
    }

    @Test
    public void testUpdatePassword() throws DAOException{
        int userId = 1;
        User user = userDAO.findById(userId);
        String password = "testUpdatePassword";
        user.setPassword(password);

        int rowsAffected = userDAO.updatePassword(user);
        int expectedAffect = 1;
        String actualPassword = userDAO.findPasswordByLogin(user.getLogin());

        assertEquals(expectedAffect, rowsAffected);
        assertEquals(password, actualPassword);
    }

}