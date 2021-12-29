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

    @Test
    public void testSaveAdminInfo() throws DAOException{
        Admin admin = new Admin();
        admin.setLogin("adminTestLogin1231231");
        admin.setPassword("pass");
        admin.setName("name");
        admin.setPhone("test");
        adminDAO.saveNew(admin);
        admin.setPassword(null);

        admin.setWorksSince(new Date(new java.util.Date().getTime()));
        admin.setNote("test note");
        int rowsAffected = adminDAO.saveInfo(admin);
        int expectedAffect = 1;
        Admin actualAdmin = adminDAO.findById(admin.getId());

        assertEquals(admin, actualAdmin);
        assertEquals(expectedAffect, rowsAffected);
    }



}