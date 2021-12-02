package com.epam.volodko.entity.user;

import java.io.Serializable;

public class Admin extends User implements Serializable {

    public Admin() {
    }

    public Admin(int id, String login, String password,
                 String name, String phone, Role role) {
        super(id, login, password, name, phone, role);
    }


}
