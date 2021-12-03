package com.epam.volodko.dao.builder.impl;

import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.entity.user.Role;
import com.epam.volodko.entity.user.RoleProvider;
import com.epam.volodko.entity.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder {

    public User build(ResultSet resultSet) throws SQLException {
        resultSet.next();
        int id = resultSet.getInt(Column.USERS_ID);
        String login = resultSet.getString(Column.USERS_LOGIN);
        String password = resultSet.getString(Column.USERS_PASSWORD);
        String name = resultSet.getString(Column.USERS_NAME);
        String phone = resultSet.getString(Column.USERS_PHONE);
        Role role = RoleProvider.getRole(resultSet.getInt(Column.USERS_ROLE_ID));
        return new User(id, login, password, name, phone, role);
    }


}
