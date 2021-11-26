package com.epam.volodko.entity.users;

import java.text.SimpleDateFormat;
import java.util.Objects;

public class DriverLicense {

    private final DriverLicenseType licenseType;
    private final SimpleDateFormat obtainingDate;
    private final String licenseNumber;

    public DriverLicense(DriverLicenseType licenseType,
                         SimpleDateFormat obtainingDate, String licenseNumber) {
        this.licenseType = licenseType;
        this.obtainingDate = obtainingDate;
        this.licenseNumber = licenseNumber;
    }

    public DriverLicenseType getLicenseType() {
        return licenseType;
    }

    public SimpleDateFormat getObtainingDate() {
        return obtainingDate;
    }

    public String getLicenseNumber() {
        return licenseNumber;
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
