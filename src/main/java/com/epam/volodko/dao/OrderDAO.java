package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.order.Order;
import com.epam.volodko.entity.user.Admin;

import java.util.List;

public interface OrderDAO {

    Order findById(int id) throws DAOException;

    List<Order> findByClientId(int clientId) throws DAOException;

    List<Order> findByAdminId(int adminId) throws DAOException;

    List<Order> findByCarId(int carId) throws DAOException;

    List<Order> findAll() throws DAOException;

    int saveNew(Order order) throws DAOException;

    int deleteById(int id) throws DAOException;

    int update(Order order) throws DAOException;

    int setAdmin(int orderId, int adminId) throws DAOException;

    int setCar(int orderId, int carId) throws DAOException;

    int updateCompleted(int orderId, boolean completed) throws DAOException;

}
