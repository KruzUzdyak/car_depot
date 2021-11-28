package com.epam.volodko.dao;

import com.epam.volodko.entity.cars.Car;
import com.epam.volodko.entity.orders.Order;
import com.epam.volodko.entity.users.Admin;
import com.epam.volodko.entity.users.Client;

import java.util.List;

public interface OrderDAO {

    Order retrieveOrderById(int orderId);

    List<Order> retrieveAllOrders();

    List<Order> retrieveOrdersByClient(Client client);

    List<Order> retrieveOrdersByAdmin(Admin admin);

    List<Order> retrieveOrdersByCar(Car car);

    List<Order> retrieveOpenOrders();

    List<Order> retrieveCompletedOrders();
}
