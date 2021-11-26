package com.epam.volodko.entity.users;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Driver extends User{

    private List<DriverLicense> licenses = new ArrayList<>();

    public Driver(int userId, String login, String password,
                  String name, String phone, Role role) {
        super(userId, login, password, name, phone, role);
    }

    public List<DriverLicense> getLicenses(){
        return licenses;
    }

    public void addLicense(DriverLicense license){
        licenses.add(license);
    }

    public void setLicenses(DriverLicense... licenses) {
        this.licenses = Arrays.asList(licenses);
    }
}
