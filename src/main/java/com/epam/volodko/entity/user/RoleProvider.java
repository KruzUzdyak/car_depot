package com.epam.volodko.entity.user;

import java.util.HashMap;
import java.util.Map;

public class RoleProvider {

    private static RoleProvider instance;

    private final Map<Integer, Role> roles;

    private RoleProvider(){
        roles = new HashMap<>();
        roles.put(Role.ADMIN.getRoleId(), Role.ADMIN);
        roles.put(Role.DRIVER.getRoleId(), Role.DRIVER);
        roles.put(Role.CLIENT.getRoleId(), Role.CLIENT);
    }

    public static Role getRole(int roleId){
        if (instance == null){
            instance = new RoleProvider();
        }
        return instance.roles.get(roleId);
    }


}
