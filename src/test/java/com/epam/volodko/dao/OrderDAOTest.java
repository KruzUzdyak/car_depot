package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.order.Order;
import com.epam.volodko.entity.user.Admin;
import com.epam.volodko.entity.user.Client;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class OrderDAOTest {

    OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();

    @Test
    public void testFindById() throws DAOException{
        int orderId = 2;
        Order order = orderDAO.findById(orderId);
        System.out.println(order);
        assertNotNull(order);
    }

    @Test
    public void testFindByClientId() throws DAOException{
        int clientId = 4;
        List<Order> orders = orderDAO.findByClientId(clientId);

        for (Order order : orders){
            System.out.println(order);
            assertNotNull(order);
        }
    }

    @Test
    public void testFindByAdminId() throws DAOException{
        int adminId = 1;
        List<Order> orders = orderDAO.findByAdminId(adminId);

        for (Order order : orders){
            System.out.println(order);
            assertNotNull(order);
        }
    }

    @Test
    public void testFindByCarId() throws DAOException{
        int carId = 4;
        List<Order> orders = orderDAO.findByCarId(carId);

        for (Order order : orders){
            System.out.println(order);
            assertNotNull(order);
        }
    }

    @Test
    public void testFindAll() throws DAOException{
        List<Order> orders = orderDAO.findAll();

        for (Order order : orders){
            assertNotNull(order);
        }
    }

    @Test
    public void testSaveNew() throws DAOException{
        Client client = new Client();
        client.setId(4);
        Order order = new Order(0, "testTown from", "test town to", 400,
                new Date(123123123123L), new Date(12312312312333L), 30, "test note",
                false, 1000, client, null, null);
        int rowsAffected = orderDAO.saveNew(order);
        int expectedAffect = 1;

        assertEquals(expectedAffect, rowsAffected);
    }

    @Test
    public void testDeleteById() throws DAOException{
        int orderId = 3;
        int rowsAffected = orderDAO.deleteById(orderId);
        int expectedAffect = 1;

        assertEquals(expectedAffect, rowsAffected);
    }

    @Test
    public void testUpdate() throws DAOException{
        Client client = new Client();
        client.setId(4);
        Order order = new Order(4, "UPDATED", "UPDATED", 400,
                new Date(123123123123L), new Date(12312312312333L), 30, "test note",
                false, 1000, client, null, null);
        int rowsAffected = orderDAO.update(order);
        int expectedAffect = 1;

        assertEquals(expectedAffect, rowsAffected);
    }

    @Test
    public void testSetAdmin() throws DAOException{
        int adminId = 1;
        int orderId = 4;
        int rowsAffected = orderDAO.setAdmin(orderId, adminId);
        int expectedAffect = 1;

        assertEquals(expectedAffect, rowsAffected);
    }

    @Test
    public void testSetCar() throws DAOException{
        int carId = 1;
        int orderId = 4;
        int rowsAffected = orderDAO.setCar(orderId, carId);
        int expectedAffect = 1;

        assertEquals(expectedAffect, rowsAffected);
    }

}