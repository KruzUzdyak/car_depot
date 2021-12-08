package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.car.CarType;

import java.util.List;

public interface CarTypeDAO {

    CarType findById(int carTypeId) throws DAOException;

    List<CarType> findAll() throws DAOException;

    int saveNew(CarType carType) throws DAOException;

    int deleteById(int carTypeId) throws DAOException;

    int update(CarType carType) throws DAOException;
}
