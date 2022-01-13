package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.user.Admin;
import com.epam.volodko.entity.user.Role;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

public class AdminDAOTest {

    UserDAO<Admin> adminDAO = DAOFactory.getInstance().getAdminDAO();

    @Test
    public void testFindById() throws DAOException{
        int adminId = 1;
        int otherId = 2;
        Admin admin = adminDAO.findById(adminId);
        Admin otherUser = adminDAO.findById(otherId);

        assertNotNull(admin);
        assertNull(otherUser);
    }

    @Test
    public void testFindByLogin() throws DAOException{
        String adminLogin = "admin1";
        String otherLogin = "driver1";
        Admin admin = adminDAO.findByLogin(adminLogin);
        Admin otherUser = adminDAO.findByLogin(otherLogin);

        assertNotNull(admin);
        assertNull(otherUser);
    }

    @Test
    public void testFindAllAdmins() throws DAOException{
        List<Admin> admins = adminDAO.findAll();

        for (Admin admin: admins){
            assertNotNull(admin);
            assertEquals(Role.ADMIN, admin.getRole());
        }
    }



}