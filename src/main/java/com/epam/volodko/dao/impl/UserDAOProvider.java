package com.epam.volodko.dao.impl;

import com.epam.volodko.entity.user.Role;

import java.util.HashMap;
import java.util.Map;

public class UserDAOProvider {

    private static UserDAOProvider instance;

    private final Map<Role, AbstractUserDAO> provider;

    private UserDAOProvider(){
        provider = new HashMap<>();
        provider.put(Role.ADMIN, new AdminDAOImpl());
        provider.put(Role.CLIENT, new ClientDAOImpl());
        provider.put(Role.DRIVER, new DriverDAOImpl());
    }

    public static AbstractUserDAO getAbstractUserDAO(Role role){
        if (instance == null) {
            instance = new UserDAOProvider();
        }
        return instance.provider.get(role);
    }
}
