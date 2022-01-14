package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.car.Car;
import com.epam.volodko.entity.user.Driver;

import java.util.List;

public interface CarDAO {

    Car findById(int carId) throws DAOException;

    Car findByDriver(int driverId) throws DAOException;

    List<Car> findAll() throws DAOException;

    int saveNew(Car car) throws DAOException;

    int deleteById(int id) throws DAOException;

    int update(Car car) throws DAOException;

}
