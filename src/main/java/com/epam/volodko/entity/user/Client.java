package com.epam.volodko.entity.user;

import java.io.Serializable;
import java.util.Objects;

public class Client extends User implements Serializable {

    private String company;
    private String note;

    public Client(){
    }

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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Client client = (Client) o;
        return Objects.equals(company, client.company) && Objects.equals(note, client.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), company, note);
    }

    @Override
    public String toString() {
        return "Client{" +
                "company='" + company + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
