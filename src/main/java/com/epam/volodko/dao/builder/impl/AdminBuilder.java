package com.epam.volodko.dao.builder.impl;

import com.epam.volodko.dao.builder.BuilderFactory;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.entity.user.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminBuilder {

    public Admin build(ResultSet resultSet) throws SQLException {
        Admin admin = new Admin();
        buildAdmin(admin, resultSet);
        return admin;
    }

    void buildAdmin(Admin admin, ResultSet resultSet) throws SQLException {
        BuilderFactory.getUserBuilder().buildUser(admin, resultSet);
        admin.setWorksSince(resultSet.getDate(Column.ADMIN_INDO_WORKS_SINCE));
        admin.setNote(resultSet.getString(Column.ADMIN_INFO_NOTE));
    }
}
