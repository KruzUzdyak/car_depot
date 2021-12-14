package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.car.CarModel;
import com.epam.volodko.entity.car.CarType;

import java.util.List;

public interface CarModelDAO {

    CarModel findById(int carModelId) throws DAOException;

    List<CarModel> findByCarType(CarType carType) throws DAOException;

    List<CarModel> findAll() throws DAOException;

    int saveNew(CarModel carModel) throws DAOException;

    int deleteById(int carModelId) throws DAOException;

    int update(CarModel carModel) throws DAOException;
}
