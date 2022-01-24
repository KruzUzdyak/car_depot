package com.epam.volodko.dao;

import com.epam.volodko.dao.impl.*;
import com.epam.volodko.entity.user.*;

import java.util.HashMap;
import java.util.Map;

public class DAOFactory {

    private final static DAOFactory instance = new DAOFactory();

    private final UserDAO<User> userDAO = new UserDAOImpl();
    private final UserDAO<Admin> adminDAO = new AdminDAOImpl();
    private final UserDAO<Client> clientDAO = new ClientDAOImpl();
    private final UserDAO<Driver> driverDAO = new DriverDAOImpl();
    private final Map<Role, UserDAO> test = new HashMap<>();
    private final DriverLicenseDAO licenseDAO = new DriverLicenseDAOImpl();
    private final CarDAO carDAO = new CarDAOImpl();
    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final CarTypeDAO carTypeDAO = new CarTypeDAOImpl();
    private final CarModelDAO carModelDAO = new CarModelDAOImpl();
    private final RefuelRecordDAO refuelRecordDAO = new RefuelRecordDAOImpl();
    private final RepairRecordDAO repairRecordDAO = new RepairRecordDAOImpl();

    private DAOFactory(){
        test.put(null, new UserDAOImpl());
        test.put(Role.ADMIN,  new AdminDAOImpl());
        test.put(Role.CLIENT, new ClientDAOImpl());
        test.put(Role.DRIVER, new DriverDAOImpl());
    }

    public static DAOFactory getInstance(){
        return instance;
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
        return test.get(role);
    }
}

