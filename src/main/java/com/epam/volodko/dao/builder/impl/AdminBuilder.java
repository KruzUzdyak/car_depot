package com.epam.volodko.dao.builder.impl;

import com.epam.volodko.dao.builder.Builder;
import com.epam.volodko.entity.user.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminBuilder implements Builder<Admin> {

    @Override
    public Admin build(ResultSet resultSet) throws SQLException {
        return null;
    }
}
