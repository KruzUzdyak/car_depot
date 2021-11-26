package com.epam.volodko.entity.users;

public abstract class User {

    private final int userId;
    private String login;
    private String password;
    private String name;
    private String phone;
    private Role role;

    public User(int id, String login, String password,
                String name, String phone, Role role) {
        this.userId = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public Role getRole() {
        return role;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
