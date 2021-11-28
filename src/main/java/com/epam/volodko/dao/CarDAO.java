package com.epam.volodko.dao;

import com.epam.volodko.entity.cars.Car;
import com.epam.volodko.entity.cars.CarModel;
import com.epam.volodko.entity.users.Driver;

import java.util.List;

public interface CarDAO {

    Car retrieveCarById(int carId);

    Car retrieveCarByDriver(Driver driver);

    List<Car> retrieveAllCars();

    List<Car> retrieveBrokenCars();

    List<Car> retrieveNonBrokenCars();

    List<Car> retrieveCarsByModel(CarModel model);

    List<Car> retrieveLowFuelCars(int lowFuelLevelPercent);
}
