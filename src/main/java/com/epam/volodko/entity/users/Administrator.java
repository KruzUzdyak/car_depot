package com.epam.volodko.entity.users;

public class Administrator extends User{

    public Administrator(int id, String login, String password,
                         String name, String phone, Role role) {
        super(id, login, password, name, phone, role);
    }


}
