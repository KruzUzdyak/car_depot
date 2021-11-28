package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.CarDAO;
import com.epam.volodko.entity.cars.Car;
import com.epam.volodko.entity.cars.CarModel;
import com.epam.volodko.entity.users.Driver;

import java.util.ArrayList;
import java.util.List;

public class CarDAOImp implements CarDAO {

    @Override
    public Car retrieveCarById(int carId) {
        //TODO
        return new Car();
    }

    @Override
    public Car retrieveCarByDriver(Driver driver) {
        //TODO
        return new Car();
    }

    @Override
    public List<Car> retrieveAllCars() {
        //TODO
        return new ArrayList<>();
    }

    @Override
    public List<Car> retrieveBrokenCars() {
        //TODO
        return new ArrayList<>();
    }

    @Override
    public List<Car> retrieveNonBrokenCars() {
        //TODO
        return new ArrayList<>();
    }

    @Override
    public List<Car> retrieveCarsByModel(CarModel model) {
        //TODO
        return new ArrayList<>();
    }

    @Override
    public List<Car> retrieveLowFuelCars(int lowFuelLevelPercent) {
        //TODO
        return new ArrayList<>();
    }
}
