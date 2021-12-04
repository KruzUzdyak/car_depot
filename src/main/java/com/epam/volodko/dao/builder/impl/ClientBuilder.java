package com.epam.volodko.dao.builder.impl;

import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.entity.user.Client;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientBuilder extends UserBuilder<Client> {

    @Override
    public Client build(ResultSet resultSet) throws SQLException {
        Client client = new Client();
        buildUser(client, resultSet);
        client.setCompany(resultSet.getString(Column.CLIENT_INFO_COMPANY));
        client.setNote(resultSet.getString(Column.CLIENT_INFO_NOTE));
        return client;
    }
}
