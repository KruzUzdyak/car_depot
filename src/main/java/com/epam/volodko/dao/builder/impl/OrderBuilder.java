package com.epam.volodko.dao.builder.impl;

import com.epam.volodko.dao.builder.Builder;
import com.epam.volodko.entity.order.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderBuilder implements Builder<Order> {

    @Override
    public Order build(ResultSet resultSet) throws SQLException {
        return null;
    }
}
