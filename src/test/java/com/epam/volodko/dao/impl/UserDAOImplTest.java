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
    public void checkFindById() throws DAOException {
        int userId = 1;
        User expectedUser = new User(userId, "admin", null, "Strelkov Viktor", "+375297766858", Role.ADMIN);
        User actualUser = userDAO.findById(userId);

        assertEquals(expectedUser, actualUser);
    }

}