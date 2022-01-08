package com.epam.volodko.entity.user;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class DriverLicense implements Serializable {

    private DriverLicenseType licenseType;
    private Date obtainingDate;
    private String licenseNumber;

    public DriverLicense(){

    }

    public DriverLicense(DriverLicenseType licenseType,
                         Date obtainingDate, String licenseNumber) {
        this.licenseType = licenseType;
        this.obtainingDate = obtainingDate;
        this.licenseNumber = licenseNumber;
    }

    public DriverLicenseType getLicenseType() {
        return licenseType;
    }

    public Date getObtainingDate() {
        return obtainingDate;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseType(DriverLicenseType licenseType) {
        this.licenseType = licenseType;
    }

    public void setObtainingDate(Date obtainingDate) {
        this.obtainingDate = obtainingDate;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DriverLicense that = (DriverLicense) o;
        return licenseType == that.licenseType && Objects.equals(obtainingDate, that.obtainingDate) &&
                Objects.equals(licenseNumber, that.licenseNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(licenseType, obtainingDate, licenseNumber);
    }

    @Override
    public String toString() {
        return "DriverLicense{" +
                "licenseType=" + licenseType +
                ", obtainingDate=" + obtainingDate +
                ", licenseNumber='" + licenseNumber + '\'' +
                '}';
    }
}
