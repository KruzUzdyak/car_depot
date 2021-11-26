package com.epam.volodko.entity.users;

public class Client extends User{

    private String company;
    private String note;

    public Client(int userId, String login, String password, String name,
                  String phone, Role role, String company, String note) {
        super(userId, login, password, name, phone, role);
        this.company = company;
        this.note = note;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
