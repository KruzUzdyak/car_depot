package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.user.Admin;
import com.epam.volodko.entity.user.User;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AdminDAOTest {

    private UserDAO adminDAO = DAOFactory.getInstance().getAdminDAO();

    @Test
    public void testFindById() throws DAOException{
        Admin admin = (Admin) adminDAO.findById(1);

        assertNotNull(admin);
        // TODO: 14.12.2021 make full test
    }

    @Test
    public void testFindByLogin() throws DAOException{
        String login = "admin1";
        Admin admin = (Admin) adminDAO.findByLogin(login);

        assertNotNull(admin);
        // TODO: 14.12.2021 make full test
    }

    @Test
    public void testFindAll() throws DAOException{
        List<User> admins = adminDAO.findAll();

        for (User admin : admins){
            System.out.println(admin);
        }

        assertFalse(admins.isEmpty());
        // TODO: 14.12.2021 make full test
    }

}