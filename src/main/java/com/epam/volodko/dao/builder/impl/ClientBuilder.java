package com.epam.volodko.dao.builder.impl;

import com.epam.volodko.dao.builder.Builder;
import com.epam.volodko.entity.user.Client;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientBuilder implements Builder<Client> {

    @Override
    public Client build(ResultSet resultSet) throws SQLException {
        return null;
    }
}
