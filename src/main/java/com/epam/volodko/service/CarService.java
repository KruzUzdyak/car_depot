package com.epam.volodko.service;

import com.epam.volodko.entity.car.Car;
import com.epam.volodko.service.exception.ServiceException;

import java.util.List;

public interface CarService {

    List<Car> getAllCars() throws ServiceException;
}
