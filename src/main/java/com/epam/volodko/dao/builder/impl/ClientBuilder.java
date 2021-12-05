package com.epam.volodko.dao.builder.impl;

import com.epam.volodko.dao.builder.BuilderFactory;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.entity.user.Client;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientBuilder {

    public Client build(ResultSet resultSet) throws SQLException {
        Client client = new Client();
        buildClient(client, resultSet);
        return client;
    }

    private void buildClient(Client client, ResultSet resultSet) throws SQLException {
        BuilderFactory.getUserBuilder().buildUser(client, resultSet);
        client.setCompany(resultSet.getString(Column.CLIENT_INFO_COMPANY));
        client.setNote(resultSet.getString(Column.CLIENT_INFO_NOTE));
    }
}
