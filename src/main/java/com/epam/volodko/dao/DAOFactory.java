package com.epam.volodko.dao;

import com.epam.volodko.dao.impl.*;

public class DAOFactory {

    private final static DAOFactory instance = new DAOFactory();

    private final UserDAO userDAO = new UserDAOImpl();
    private final CarDAO carDAO = new CarDAOImpl();
    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final DriverLicenseDAO driverLicenseDAO = new DriverLicenseDAOImpl();
    private final AdminDAO adminDAO = new AdminDAOImpl();

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

    public DriverLicenseDAO getDriverLicenseDAO() {
        return driverLicenseDAO;
    }

    public AdminDAO getAdminDAO() {
        return adminDAO;
    }
}
