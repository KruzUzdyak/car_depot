package com.epam.volodko.dao.impl.builders;

import com.epam.volodko.entity.users.Role;
import com.epam.volodko.entity.users.RoleProvider;
import com.epam.volodko.entity.users.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder {

    public User buildUser(ResultSet resultSet) throws SQLException {
        resultSet.next();
        int id = resultSet.getInt(UserTableColumn.USER_ID);
        String login = resultSet.getString(UserTableColumn.LOGIN);
        String password = resultSet.getString(UserTableColumn.PASSWORD);
        String name = resultSet.getString(UserTableColumn.NAME);
        String phone = resultSet.getString(UserTableColumn.PHONE);
        Role role = RoleProvider.getRole(resultSet.getInt(UserTableColumn.ROLE_ID));
        return new User(id, login, password, name, phone, role);
    }


}
