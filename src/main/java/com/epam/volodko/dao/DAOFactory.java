package com.epam.volodko.dao;

import com.epam.volodko.dao.impl.*;

public class DAOFactory {

    private final static DAOFactory instance = new DAOFactory();

    private final UserDAO userDAO = new UserDAOImpl();
    private final UserDAO adminDAO = new AdminDAOImpl();
    private final UserDAO clientDAO = new ClientDAOImpl();
    private final UserDAO driverDAO = new DriverDAOImpl();
    private final CarDAO carDAO = new CarDAOImpl();
    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final CarTypeDAO carTypeDAO = new CarTypeDAOImpl();
    private final CarModelDAO carModelDAO = new CarModelDAOImpl();

    private DAOFactory(){
    }

    public static DAOFactory getInstance(){
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public UserDAO getAdminDAO() {
        return adminDAO;
    }

    public UserDAO getClientDAO() {
        return clientDAO;
    }

    public UserDAO getDriverDAO() {
        return driverDAO;
    }

    public CarDAO getCarDAO() {
        return carDAO;
    }

    public OrderDAO getOrderDAO() {
        return orderDAO;
    }

    public CarTypeDAO getCarTypeDAO() {
        return carTypeDAO;
    }

    public CarModelDAO getCarModelDAO() {
        return carModelDAO;
    }
}
