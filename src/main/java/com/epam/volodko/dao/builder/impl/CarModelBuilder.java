package com.epam.volodko.dao.builder.impl;

import com.epam.volodko.dao.builder.Builder;
import com.epam.volodko.entity.car.CarModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarModelBuilder implements Builder<CarModel> {

    @Override
    public CarModel build(ResultSet resultSet) throws SQLException {
        return null;
    }
}
