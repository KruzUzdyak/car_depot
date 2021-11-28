package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.OrderDAO;
import com.epam.volodko.entity.cars.Car;
import com.epam.volodko.entity.orders.Order;
import com.epam.volodko.entity.users.Admin;
import com.epam.volodko.entity.users.Client;

import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public Order retrieveOrderById(int orderId) {
        //TODO
        return new Order();
    }

    @Override
    public List<Order> retrieveAllOrders() {
        //TODO
        return new ArrayList<>();
    }

    @Override
    public List<Order> retrieveOrdersByClient(Client client) {
        //TODO
        return new ArrayList<>();
    }

    @Override
    public List<Order> retrieveOrdersByAdmin(Admin admin) {
        //TODO
        return new ArrayList<>();
    }

    @Override
    public List<Order> retrieveOrdersByCar(Car car) {
        //TODO
        return new ArrayList<>();
    }

    @Override
    public List<Order> retrieveOpenOrders() {
        //TODO
        return new ArrayList<>();
    }

    @Override
    public List<Order> retrieveCompletedOrders() {
        //TODO
        return new ArrayList<>();
    }
}
