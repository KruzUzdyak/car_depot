package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.DAOFactory;
import com.epam.volodko.dao.UserDAO;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.user.Role;
import com.epam.volodko.entity.user.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDAOImplTest {

    UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

    @Test
    public void checkRetrieveUsersCount() throws DAOException {
        int expectedUserCount = 4;
        int actualUserCount = userDAO.retrieveUserCount();
        assertEquals(expectedUserCount, actualUserCount);
    }

    @Test
    public void checkRetrieveUserById() throws DAOException {
        int userId = 1;
        User expectedUser = new User(1,"admin", "admin", "Strelkov Viktor", "+375297766858", Role.ADMIN);
        User actualUser = userDAO.retrieveUserById(userId);
        assertEquals(expectedUser, actualUser);
    }


}