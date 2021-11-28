package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.UserDAO;
import com.epam.volodko.dao.database.ConnectionPoolFactory;
import com.epam.volodko.dao.database.pool_exception.ConnectionPoolException;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.users.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public User retrieveUserById(int userId) throws DAOException{

        User user;
        try {
            Connection con = ConnectionPoolFactory.getConnectionPool().takeConnection();
            Statement st = con.createStatement();
            String query = String.format("SELECT * FROM USERS WHERE user_id = %d;", userId);
            ResultSet resultSet = st.executeQuery(query);
            resultSet.next();
            int id = resultSet.getInt("user_id");
            String login = resultSet.getString("login");
            String password = resultSet.getString("password");
            String name = resultSet.getString("name");
            String phone = resultSet.getString("phone");
//            Role role = Role.valueOf(resultSet.getString("role_id"));
            user = new User(id, login, password, name, phone, Role.ADMIN);

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        }
        return user;
    }

    @Override
    public List<User> retrieveAllUsers() {
        return new ArrayList<>();
    }

    @Override
    public List<Admin> retrieveAllAdmins() {
        return new ArrayList<>();
    }

    @Override
    public List<Client> retrieveAllClients() {
        return new ArrayList<>();
    }

    @Override
    public List<Driver> retrieveAllDrivers() {
        return new ArrayList<>();
    }


}
