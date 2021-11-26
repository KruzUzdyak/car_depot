package com.epam.volodko.entity.users;

import java.util.HashMap;
import java.util.Map;

public class RoleProvider {

    private static RoleProvider instance;

    private final Map<Integer, Role> roles;

    private RoleProvider(){
        roles = new HashMap<>();
        roles.put(1, Role.ADMINISTRATOR);
        roles.put(2, Role.DRIVER);
        roles.put(3, Role.CLIENT);
    }

    public static Role getRole(int roleId){
        if (instance == null){
            instance = new RoleProvider();
        }
        return instance.roles.get(roleId);
    }


}
