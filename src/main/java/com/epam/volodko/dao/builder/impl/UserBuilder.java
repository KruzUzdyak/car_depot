package com.epam.volodko.dao.builder.impl;

import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.entity.user.Role;
import com.epam.volodko.entity.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder {

    public User build(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getInt(Column.USERS_ID));
        user.setLogin(resultSet.getString(Column.USERS_LOGIN));
        user.setName(resultSet.getString(Column.USERS_NAME));
        user.setPhone(resultSet.getString(Column.USERS_PHONE));
        user.setRole(Role.valueOf(resultSet.getString(Column.ROLES_ROLE).toUpperCase()));
        return user;
    }
}
