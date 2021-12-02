package com.epam.volodko.dao.impl.builder;

import com.epam.volodko.dao.impl.table_name.UserColumn;
import com.epam.volodko.entity.user.Role;
import com.epam.volodko.entity.user.RoleProvider;
import com.epam.volodko.entity.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder {

    private static UserBuilder instance;

    private UserBuilder(){
    }

    public static UserBuilder getInstance() {
        if (instance == null){
            instance = new UserBuilder();
        }
        return instance;
    }

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
