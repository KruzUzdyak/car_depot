package com.epam.volodko.entity.user;

public enum Role {

    ADMIN(1),
    DRIVER(2),
    CLIENT(3);

    final int roleId;

    Role(int roleId){
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }
}