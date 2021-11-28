package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.users.Admin;
import com.epam.volodko.entity.users.Client;
import com.epam.volodko.entity.users.Driver;
import com.epam.volodko.entity.users.User;

import java.util.List;

public interface UserDAO {

    User retrieveUserById(int userId) throws DAOException;

    List<User> retrieveAllUsers();

    List<Admin> retrieveAllAdmins();

    List<Client> retrieveAllClients();

    List<Driver> retrieveAllDrivers();


}
