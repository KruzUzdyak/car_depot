package com.epam.volodko.dao.builder;

import com.epam.volodko.dao.builder.impl.UserBuilder;

public class BuilderFactory {

    private static final UserBuilder userBuilder = new UserBuilder();

    public static UserBuilder getUserBuilder() {
        return userBuilder;
    }
}
