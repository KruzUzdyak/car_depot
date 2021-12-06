package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.car.Car;
import com.epam.volodko.entity.user.Driver;

import java.util.List;

public interface CarDAO {

    Car findCarById(int carId) throws DAOException;

    Car findCarByDriver(Driver driver) throws DAOException;

    List<Car> findAllCars() throws DAOException;

    int saveNewCar(Car car) throws DAOException;

    int deleteCar(Car car) throws DAOException;

    int updateCar(Car car) throws DAOException;

}
