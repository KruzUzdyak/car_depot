package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.UserDAO;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.users.Role;
import com.epam.volodko.entity.users.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDAOImplTest {

    UserDAO userDAO = new UserDAOImpl();

    @Test
    public void checkRetrieveUserById() throws DAOException {
        int userId = 1;
        User user = userDAO.retrieveUserById(userId);

        assertNotNull(user);
        assertEquals(userId, user.getUserId());
        assertEquals("admin" ,user.getLogin());
        assertNotNull(user.getPassword());
        assertNotNull(user.getName());
        assertNotNull(user.getPhone());
        assertNotNull(user.getRole());
    }

    @Test
    public void checkSaveUser() throws DAOException {
        String userName = "Denis";
        User user = new User(0, "Denis", "Kul", userName, "+37529666000", Role.ADMIN);
        userDAO.saveUser(user);

        User actualUser = userDAO.retrieveUserByName(userName);
        assertEquals(userName, actualUser.getName());
    }

}