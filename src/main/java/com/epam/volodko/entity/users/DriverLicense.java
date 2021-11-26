package com.epam.volodko.entity.users;

import java.text.SimpleDateFormat;

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
}
