package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.UserDAO;
import com.epam.volodko.dao.exception.DAOException;
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

}