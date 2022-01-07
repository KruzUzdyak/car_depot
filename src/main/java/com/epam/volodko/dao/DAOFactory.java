package com.epam.volodko.dao;

import com.epam.volodko.dao.impl.*;
import com.epam.volodko.entity.user.*;

public class DAOFactory {

    private final static DAOFactory instance = new DAOFactory();

    private final UserDAO<User> userDAO = new UserDAOImpl();
    private final UserDAO<Admin> adminDAO = new AdminDAOImpl();
    private final UserDAO<Client> clientDAO = new ClientDAOImpl();
    private final UserDAO<Driver> driverDAO = new DriverDAOImpl();
    private final DriverLicenseDAO licenseDAO = new DriverLicenseDAOImpl();
    private final CarDAO carDAO = new CarDAOImpl();
    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final CarTypeDAO carTypeDAO = new CarTypeDAOImpl();
    private final CarModelDAO carModelDAO = new CarModelDAOImpl();
    private final RefuelRecordDAO refuelRecordDAO = new RefuelRecordDAOImpl();
    private final RepairRecordDAO repairRecordDAO = new RepairRecordDAOImpl();

    private DAOFactory(){
    }

    public static DAOFactory getInstance(){
        return instance;
    }

    public UserDAO<User> getUserDAO() {
        return userDAO;
    }

    public UserDAO<Admin> getAdminDAO() {
        return adminDAO;
    }

    public UserDAO<Client> getClientDAO() {
        return clientDAO;
    }

    public UserDAO<Driver> getDriverDAO() {
        return driverDAO;
    }

    public DriverLicenseDAO getLicenseDAO() {
        return licenseDAO;
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

    public RefuelRecordDAO getRefuelRecordDAO() {
        return refuelRecordDAO;
    }

    public RepairRecordDAO getRepairRecordDAO() {
        return repairRecordDAO;
    }

    public UserDAO getUserDAO(Role role){
        switch (role){
            case ADMIN -> {
                return adminDAO;
            }
            case CLIENT -> {
                return clientDAO;
            }
            case DRIVER -> {
                return driverDAO;
            }
        }
        return userDAO;
    }
}

