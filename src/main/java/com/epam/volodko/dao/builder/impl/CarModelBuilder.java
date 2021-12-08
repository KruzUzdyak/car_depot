package com.epam.volodko.dao.builder.impl;

import com.epam.volodko.dao.builder.BuilderFactory;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.entity.car.CarModel;
import com.epam.volodko.entity.car.CarType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarModelBuilder {

    public CarModel build(ResultSet resultSet) throws DAOException {
        CarModel carModel;
        try {
            int carModelId = resultSet.getInt(Column.CAR_MODELS_ID);
            String modelName = resultSet.getString(Column.CAR_MODELS_NAME);
            int capacity = resultSet.getInt(Column.CAR_MODELS_CAPACITY);
            int fuelTank = resultSet.getInt(Column.CAR_MODELS_FUEL_TANK);
            String loadType = resultSet.getString(Column.CAR_MODELS_LOAD_TYPE);
            CarType carType = BuilderFactory.getCarTypeBuilder().build(resultSet);
            carModel = new CarModel(carModelId, modelName, capacity, fuelTank, loadType, carType);
        } catch (SQLException e){
            throw new DAOException("SQLException when building car model.", e);
        }
        return carModel;
    }
}
