package com.epam.volodko.dao.builder.impl;

import com.epam.volodko.dao.builder.Builder;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.entity.user.Role;
import com.epam.volodko.entity.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UserBuilder<T extends User> implements Builder<User> {

    public void buildUser(T t, ResultSet resultSet) throws SQLException {
        t.setUserId(resultSet.getInt(Column.USERS_ID));
        t.setLogin(resultSet.getString(Column.USERS_LOGIN));
        t.setName(resultSet.getString(Column.USERS_NAME));
        t.setPhone(resultSet.getString(Column.USERS_PHONE));
        t.setRole(Role.valueOf(resultSet.getString(Column.ROLES_ROLE).toUpperCase()));
    }
}
