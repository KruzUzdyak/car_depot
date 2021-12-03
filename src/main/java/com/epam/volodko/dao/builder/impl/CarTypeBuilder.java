package com.epam.volodko.dao.builder.impl;

import com.epam.volodko.dao.builder.Builder;
import com.epam.volodko.entity.car.CarType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarTypeBuilder implements Builder<CarType> {

    @Override
    public CarType build(ResultSet resultSet) throws SQLException {
        return null;
    }
}
