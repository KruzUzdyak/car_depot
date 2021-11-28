package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.UserDAO;
import com.epam.volodko.entity.users.Admin;
import com.epam.volodko.entity.users.Client;
import com.epam.volodko.entity.users.Driver;
import com.epam.volodko.entity.users.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public User retrieveUserById(int userId) {
        return new Admin();
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
