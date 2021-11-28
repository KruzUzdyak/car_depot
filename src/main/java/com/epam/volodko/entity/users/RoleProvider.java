package com.epam.volodko.entity.users;

import java.util.HashMap;
import java.util.Map;

public class RoleProvider {

    private static RoleProvider instance;

    private final Map<Integer, Role> roles;

    private RoleProvider(){
        roles = new HashMap<>();
        roles.put(Role.ADMIN.getRole_id(), Role.ADMIN);
        roles.put(Role.DRIVER.getRole_id(), Role.DRIVER);
        roles.put(Role.CLIENT.getRole_id(), Role.CLIENT);
    }

    public static Role getRole(int roleId){
        if (instance == null){
            instance = new RoleProvider();
        }
        return instance.roles.get(roleId);
    }


}
