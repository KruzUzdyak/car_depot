package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.car.Car;
import com.epam.volodko.entity.car.CarModel;
import com.epam.volodko.entity.user.Driver;

import java.util.List;

public interface CarDAO {

    Car retrieveCarById(int carId) throws DAOException;

    Car retrieveCarByDriver(Driver driver) throws DAOException;

    Car retrieveCarByPlateNumber(String plateNumber) throws DAOException;

    List<Car> retrieveAllCars() throws DAOException;

    List<Car> retrieveBrokenCars() throws DAOException;

    List<Car> retrieveNonBrokenCars() throws DAOException;

    List<Car> retrieveCarsByModel(CarModel model) throws DAOException;

    List<Car> retrieveLowFuelCars(int lowFuelLevelPercent) throws DAOException;

    void saveCar(Car car) throws DAOException;

    void deleteCar(Car car) throws DAOException;
}
