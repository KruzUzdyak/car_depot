package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.OrderDAO;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.car.Car;
import com.epam.volodko.entity.order.Order;
import com.epam.volodko.entity.user.Admin;
import com.epam.volodko.entity.user.Client;

import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public Order retrieveOrderById(int orderId) throws DAOException {
        //TODO
        return new Order();
    }

    @Override
    public List<Order> retrieveAllOrders() throws DAOException {
        //TODO
        return new ArrayList<>();
    }

    @Override
    public List<Order> retrieveOrdersByClient(Client client) throws DAOException {
        //TODO
        return new ArrayList<>();
    }

    @Override
    public List<Order> retrieveOrdersByAdmin(Admin admin) throws DAOException {
        //TODO
        return new ArrayList<>();
    }

    @Override
    public List<Order> retrieveOrdersByCar(Car car) throws DAOException {
        //TODO
        return new ArrayList<>();
    }

    @Override
    public List<Order> retrieveOpenOrders() throws DAOException {
        //TODO
        return new ArrayList<>();
    }

    @Override
    public List<Order> retrieveCompletedOrders() throws DAOException {
        //TODO
        return new ArrayList<>();
    }

    @Override
    public List<Order> retrieveOrdersByDestFrom(String destFrom) throws DAOException {
        //TODO
        return new ArrayList<>();
    }

    @Override
    public List<Order> retrieveOrdersByDestTo(String destTo) throws DAOException {
        //TODO
        return new ArrayList<>();    }

    @Override
    public void saveOrder(Order order) throws DAOException {
        //TODO
    }

    @Override
    public void deleteOrderById(int orderId) throws DAOException {
        //TODO
    }
}
