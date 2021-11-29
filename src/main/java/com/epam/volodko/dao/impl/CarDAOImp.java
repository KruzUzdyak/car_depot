package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.CarDAO;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.car.Car;
import com.epam.volodko.entity.car.CarModel;
import com.epam.volodko.entity.user.Driver;

import java.util.ArrayList;
import java.util.List;

public class CarDAOImp implements CarDAO {

    @Override
    public Car retrieveCarById(int carId) throws DAOException {
        //TODO
        return new Car();
    }

    @Override
    public Car retrieveCarByDriver(Driver driver) throws DAOException {
        //TODO
        return new Car();
    }

    @Override
    public Car retrieveCarByPlateNumber(String plateNumber) throws DAOException {
        //TODO
        return new Car();
    }

    @Override
    public List<Car> retrieveAllCars() throws DAOException {
        //TODO
        return new ArrayList<>();
    }

    @Override
    public List<Car> retrieveBrokenCars() throws DAOException {
        //TODO
        return new ArrayList<>();
    }

    @Override
    public List<Car> retrieveNonBrokenCars() throws DAOException {
        //TODO
        return new ArrayList<>();
    }

    @Override
    public List<Car> retrieveCarsByModel(CarModel model) throws DAOException {
        //TODO
        return new ArrayList<>();
    }

    @Override
    public List<Car> retrieveLowFuelCars(int lowFuelLevelPercent) throws DAOException {
        //TODO
        return new ArrayList<>();
    }

    @Override
    public void saveCar(Car car) throws DAOException {
        //TODO
    }

    @Override
    public void deleteCar(Car car) throws DAOException {
        //TODO
    }
}
