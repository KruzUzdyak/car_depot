package com.epam.volodko.dao.builder.impl;

import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.entity.user.Admin;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminBuilder extends UserBuilder{

    public Admin build(ResultSet resultSet) throws SQLException {
        Admin admin = new Admin();
        buildAdmin(admin, resultSet);
        return admin;
    }

    void buildAdmin(Admin admin, ResultSet resultSet) throws SQLException {
        buildUser(admin, resultSet);
        long workSince = resultSet.getLong(Column.ADMIN_INFO_WORKS_SINCE);
        if (workSince > 0){
            admin.setWorksSince(new Date(workSince));
        } else {
            admin.setWorksSince(null);
        }
        admin.setNote(resultSet.getString(Column.ADMIN_INFO_NOTE));
    }
}
