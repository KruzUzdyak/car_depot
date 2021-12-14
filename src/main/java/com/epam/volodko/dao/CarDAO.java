package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.car.Car;
import com.epam.volodko.entity.user.Driver;

import java.util.List;

public interface CarDAO {

    Car findById(int carId) throws DAOException;

    Car findByDriver(Driver driver) throws DAOException;

    List<Car> findAll() throws DAOException;

    int saveNew(Car car) throws DAOException;

    int delete(Car car) throws DAOException;

    int update(Car car) throws DAOException;

}
