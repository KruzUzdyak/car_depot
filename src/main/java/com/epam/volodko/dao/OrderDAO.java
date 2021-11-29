package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.car.Car;
import com.epam.volodko.entity.order.Order;
import com.epam.volodko.entity.user.Admin;
import com.epam.volodko.entity.user.Client;

import java.util.List;

public interface OrderDAO {

    Order retrieveOrderById(int orderId) throws DAOException;

    List<Order> retrieveAllOrders() throws DAOException;

    List<Order> retrieveOrdersByClient(Client client) throws DAOException;

    List<Order> retrieveOrdersByAdmin(Admin admin) throws DAOException;

    List<Order> retrieveOrdersByCar(Car car) throws DAOException;

    List<Order> retrieveOpenOrders() throws DAOException;

    List<Order> retrieveCompletedOrders() throws DAOException;

    List<Order> retrieveOrdersByDestFrom(String destFrom) throws DAOException;

    List<Order> retrieveOrdersByDestTo(String destTo) throws DAOException;

    void saveOrder(Order order) throws DAOException;

    void deleteOrderById(int orderId) throws DAOException;
}
