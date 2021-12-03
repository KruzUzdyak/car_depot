package com.epam.volodko.dao.builder.impl;

import com.epam.volodko.dao.builder.Builder;
import com.epam.volodko.entity.car.Car;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarBuilder implements Builder<Car> {

    @Override
    public Car build(ResultSet resultSet) throws SQLException {
        return null;
    }
}
