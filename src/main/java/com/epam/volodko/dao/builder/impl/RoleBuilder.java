package com.epam.volodko.dao.builder.impl;

import com.epam.volodko.dao.builder.Builder;
import com.epam.volodko.entity.user.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleBuilder implements Builder<Role> {

    @Override
    public Role build(ResultSet resultSet) throws SQLException {
        return null;
    }
}
