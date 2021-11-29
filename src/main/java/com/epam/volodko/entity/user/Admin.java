package com.epam.volodko.entity.user;

public class Admin extends User{

    public Admin(){
    }

    public Admin(int id, String login, String password,
                 String name, String phone, Role role) {
        super(id, login, password, name, phone, role);
    }


}
