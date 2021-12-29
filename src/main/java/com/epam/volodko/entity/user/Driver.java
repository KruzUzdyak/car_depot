package com.epam.volodko.entity.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Driver extends User implements Serializable {

    private List<DriverLicense> licenses = new ArrayList<>();

    public Driver(){
        setRole(Role.DRIVER);
    }

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
        Driver driver = (Driver) o;
        return Objects.equals(licenses, driver.licenses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), licenses);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "licenses=" + licenses +
                "} " + super.toString();
    }
}
