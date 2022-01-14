package com.epam.volodko.service.impl;

import com.epam.volodko.dao.CarDAO;
import com.epam.volodko.dao.DAOFactory;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.car.Car;
import com.epam.volodko.service.CarService;
import com.epam.volodko.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CarServiceImpl implements CarService {

    private final CarDAO carDAO = DAOFactory.getInstance().getCarDAO();

    @Override
    public Car getCarById(int carId) throws ServiceException {
        return null;
    }

    @Override
    public Car getCarByDriverId(int driverId) throws ServiceException {
        if (driverId == 0){
            throw new ServiceException("Driver id is null");
        }
        Car car;
        try {
            car = carDAO.findByDriver(driverId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return car;
    }

    @Override
    public List<Car> getAllCars() throws ServiceException {
        List<Car> cars;
        try {
            cars = carDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return cars;
    }
}
