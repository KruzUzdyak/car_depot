package com.epam.volodko.entity.user;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Admin extends User implements Serializable {

    private Date worksSince;
    private String note;

    public Admin() {
    }

    public Admin(int id, String login, String password, String name,
                 String phone, Role role, Date worksSince, String note) {
        super(id, login, password, name, phone, role);
        this.worksSince = worksSince;
        this.note = note;
    }

    public Date getWorksSince() {
        return worksSince;
    }

    public void setWorksSince(Date worksSince) {
        this.worksSince = worksSince;
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
        Admin admin = (Admin) o;
        return Objects.equals(worksSince, admin.worksSince) && Objects.equals(note, admin.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), worksSince, note);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "worksSince=" + worksSince +
                ", note='" + note + '\'' +
                '}';
    }
}
