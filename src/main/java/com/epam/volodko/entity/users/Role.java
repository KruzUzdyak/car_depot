package com.epam.volodko.entity.users;

public enum Role {

    ADMIN(1),
    DRIVER(2),
    CLIENT(3);

    final int role_id;

    Role(int role_id){
        this.role_id = role_id;
    }

    public int getRole_id() {
        return role_id;
    }
}