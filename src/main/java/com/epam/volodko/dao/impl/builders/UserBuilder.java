package com.epam.volodko.dao.impl.builders;

import com.epam.volodko.dao.impl.table_column_name.UserColumn;
import com.epam.volodko.entity.users.Role;
import com.epam.volodko.entity.users.RoleProvider;
import com.epam.volodko.entity.users.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder {

    public User buildUser(ResultSet resultSet) throws SQLException {
        resultSet.next();
        int id = resultSet.getInt(UserColumn.USER_ID);
        String login = resultSet.getString(UserColumn.LOGIN);
        String password = resultSet.getString(UserColumn.PASSWORD);
        String name = resultSet.getString(UserColumn.NAME);
        String phone = resultSet.getString(UserColumn.PHONE);
        Role role = RoleProvider.getRole(resultSet.getInt(UserColumn.ROLE_ID));
        return new User(id, login, password, name, phone, role);
    }


}
