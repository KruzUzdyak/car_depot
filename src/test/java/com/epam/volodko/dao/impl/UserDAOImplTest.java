package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.DAOFactory;
import com.epam.volodko.dao.UserDAO;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.user.Admin;
import com.epam.volodko.entity.user.Role;
import com.epam.volodko.entity.user.User;
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
        Admin admin = new Admin(100, "testLogin1", "testPasswprd1", "testName1",
                "testPhpne1", Role.ADMIN, new Date(29389391211L), "testNote1");
        userDAO.saveNewUser(admin);
    }
}