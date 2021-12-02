package com.epam.volodko.dao.builder;

public class BuilderFactory {

    private static final UserBuilder userBuilder = new UserBuilder();

    public static UserBuilder getUserBuilder() {
        return userBuilder;
    }
}
