package com.epam.volodko.service.impl;

import com.epam.volodko.controller.constant.ParameterName;
import com.epam.volodko.dao.DAOFactory;
import com.epam.volodko.dao.OrderDAO;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.order.Order;
import com.epam.volodko.service.OrderService;
import com.epam.volodko.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final Logger log = LogManager.getLogger(OrderServiceImpl.class);

    private final OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();

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

    private List<Order> processOrderListRequest(String orderListType, int id) throws DAOException {
        List<Order> orders = new ArrayList<>();
        switch (orderListType) {
            case ParameterName.ORDER_LIST_ALL -> orders = orderDAO.findAll();
            case ParameterName.ORDER_LIST_ADMIN -> orders = orderDAO.findByAdminId(id);
            case ParameterName.ORDER_LIST_CLIENT -> orders = orderDAO.findByClientId(id);
            case ParameterName.ORDER_LIST_CAR -> orders = orderDAO.findByCarId(id);
        }
        return orders;
    }
}
