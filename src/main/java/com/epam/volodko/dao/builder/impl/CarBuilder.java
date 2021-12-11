package com.epam.volodko.dao.builder.impl;

import com.epam.volodko.dao.builder.BuilderFactory;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.entity.car.Car;
import com.epam.volodko.entity.user.Driver;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarBuilder {

    public Car build(ResultSet resultSet) throws DAOException {
        Car car = new Car();
        try {
            car.setCarId(resultSet.getInt(Column.CARS_ID));
            car.setPlateNumber(resultSet.getString(Column.CARS_PLATE_NUMBER));
            car.setFuelLevel(resultSet.getInt(Column.CARS_FUEL_LEVEL));
            car.setMileage(resultSet.getInt(Column.CARS_MILEAGE));
            car.setBroken(resultSet.getBoolean(Column.CARS_BROKEN));
            car.setModel(BuilderFactory.getModelBuilder().build(resultSet));
            if (resultSet.getInt(Column.CARS_DRIVER_ID) != 0) {
                Driver driver = BuilderFactory.getDriverBuilder().build(resultSet);
                car.setDriver(driver);
            }
        } catch (SQLException e) {
            throw new DAOException("SQLException when build a car.", e);
        }
        return car;
    }
}