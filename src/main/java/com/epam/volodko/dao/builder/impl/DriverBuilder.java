package com.epam.volodko.dao.builder.impl;

import com.epam.volodko.dao.builder.Builder;
import com.epam.volodko.entity.user.Driver;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverBuilder implements Builder<Driver> {

    @Override
    public Driver build(ResultSet resultSet) throws SQLException {
        return null;
    }
}
