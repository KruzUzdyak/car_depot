package com.epam.volodko.dao.builder;

import com.epam.volodko.dao.builder.impl.RoleBuilder;
import com.epam.volodko.dao.builder.impl.UserBuilder;

public class BuilderFactory {

    private static final UserBuilder userBuilder = new UserBuilder();
    private static final RoleBuilder roleBuilder = new RoleBuilder();

    public static UserBuilder getUserBuilder() {
        return userBuilder;
    }

    public static RoleBuilder getRoleBuilder() {
        return roleBuilder;
    }
}
