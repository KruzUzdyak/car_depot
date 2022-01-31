package com.epam.volodko.service;

import com.epam.volodko.entity.order.Order;
import com.epam.volodko.service.exception.ServiceException;

import java.util.List;

public interface OrderService {

    List<Order> getOrderList(String orderListType, int id) throws ServiceException;

    Order getOrderById(int id) throws ServiceException;

    boolean saveOrder(Order order) throws ServiceException;

    boolean update(Order order) throws ServiceException;

    boolean setAdmin(int orderId, int adminId) throws ServiceException;

    boolean setCar(int orderId, int carId) throws ServiceException;

    boolean updateCompleted(int orderId, boolean completed) throws ServiceException;
}
