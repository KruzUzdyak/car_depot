package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.cars.Car;
import com.epam.volodko.entity.cars.CarModel;
import com.epam.volodko.entity.users.Driver;

import java.util.List;

public interface CarDAO {

    Car retrieveCarById(int carId) throws DAOException;

    Car retrieveCarByDriver(Driver driver) throws DAOException;

    List<Car> retrieveAllCars() throws DAOException;

    List<Car> retrieveBrokenCars() throws DAOException;

    List<Car> retrieveNonBrokenCars() throws DAOException;

    List<Car> retrieveCarsByModel(CarModel model) throws DAOException;

    List<Car> retrieveLowFuelCars(int lowFuelLevelPercent) throws DAOException;
}
