package com.epam.volodko.service.impl;

import com.epam.volodko.controller.constant.ParameterName;
import com.epam.volodko.dao.DAOFactory;
import com.epam.volodko.dao.OrderDAO;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.car.Car;
import com.epam.volodko.entity.order.Order;
import com.epam.volodko.service.OrderService;
import com.epam.volodko.service.exception.ServiceException;
import com.epam.volodko.service.validator.OrderValidator;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
    private final OrderValidator validator = new OrderValidator();

    @Override
    public List<Order> getOrderList(String orderListType, int id) throws ServiceException {
        List<Order> orders;
        try{
            orders = processOrderListRequest(orderListType, id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return orders;
    }

    @Override
    public Order getOrderById(int id) throws ServiceException {
        Order order;
        try {
            order= orderDAO.findById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return order;
    }

    @Override
    public boolean saveOrder(Order order) throws ServiceException{
        if (!validator.validateForSave(order)){
            return false;
        }
        int rowsAffected;
        try {
            rowsAffected = orderDAO.saveNew(order);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return rowsAffected > 0;
    }

    @Override
    public boolean update(Order order) throws ServiceException {
        if (!validator.validateUpdate(order)){
            return false;
        }
        int rowsAffected;
        try{
            rowsAffected = orderDAO.update(order);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return rowsAffected > 0;
    }

    @Override
    public boolean setAdmin(int orderId, int adminId) throws ServiceException {
        if (!validator.validateSetEntity(orderId, adminId)){
            return false;
        }
        int rowsAffected;
        try{
            rowsAffected = orderDAO.setAdmin(orderId, adminId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return rowsAffected > 0;
    }

    @Override
    public boolean setCar(int orderId, int carId) throws ServiceException {
        if (!validator.validateSetEntity(orderId, carId)){
            return false;
        }
        int rowsAffected;
        try{
            rowsAffected = orderDAO.setCar(orderId, carId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return rowsAffected > 0;
    }

    @Override
    public boolean updateCompleted(int orderId, boolean completed) throws ServiceException {
        if (orderId <= 0){
            return false;
        }
        int rowsAffected;
        try {
            rowsAffected = orderDAO.updateCompleted(orderId, completed);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return rowsAffected > 0;
    }

    private List<Order> processOrderListRequest(String orderListType, int userId) throws DAOException {
        List<Order> orders = new ArrayList<>();
        switch (orderListType) {
            case ParameterName.ORDER_LIST_ALL -> orders = orderDAO.findAll();
            case ParameterName.ORDER_LIST_ADMIN -> orders = orderDAO.findByAdminId(userId);
            case ParameterName.ORDER_LIST_CLIENT -> orders = orderDAO.findByClientId(userId);
            case ParameterName.ORDER_LIST_CAR -> {
                Car car = DAOFactory.getInstance().getCarDAO().findByDriver(userId);
                orders = orderDAO.findByCarId(car.getId());
            }
        }
        return orders;
    }


}
