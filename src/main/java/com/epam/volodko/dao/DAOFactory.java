package com.epam.volodko.dao;

import com.epam.volodko.dao.impl.CarDAOImp;
import com.epam.volodko.dao.impl.OrderDAOImpl;
import com.epam.volodko.dao.impl.UserDAOImpl;

public class DAOFactory {

    private final static DAOFactory instance = new DAOFactory();

    private final UserDAO userDAO = new UserDAOImpl();
    private final CarDAO carDAO = new CarDAOImp();
    private final OrderDAO orderDAO = new OrderDAOImpl();

    private DAOFactory(){
    }

    public static DAOFactory getInstance(){
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public CarDAO getCarDAO() {
        return carDAO;
    }

    public OrderDAO getOrderDAO() {
        return orderDAO;
    }
}
