package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.car.CarModel;
import com.epam.volodko.entity.car.CarType;

import java.util.List;

public interface CarModelDAO {

    CarModel getById(int carModelId) throws DAOException;

    CarModel getByCarType(CarType carType) throws DAOException;

    List<CarModel> getAll() throws DAOException;

    int saveNew(CarModel carModel) throws DAOException;

    int deleteById(int carModelId) throws DAOException;

    int update(CarModel carModel) throws DAOException;
}
