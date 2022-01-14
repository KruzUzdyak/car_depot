package com.epam.volodko.service;

import com.epam.volodko.entity.car.Car;
import com.epam.volodko.service.exception.ServiceException;

import java.util.List;

public interface CarService {

    Car getCarById(int carId) throws ServiceException;

    Car getCarByDriverId(int driverId) throws ServiceException;

    List<Car> getAllCars() throws ServiceException;
}
