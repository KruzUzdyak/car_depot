package com.epam.volodko.entity.car;

import com.epam.volodko.entity.user.DriverLicenseType;

public enum CarType {

    SMALL_BUS(DriverLicenseType.B),
    BUS(DriverLicenseType.D),
    SMALL_TRUCK(DriverLicenseType.C),
    TRUCK(DriverLicenseType.CE);


    private final DriverLicenseType requiredDriverLicenseType;

    CarType(DriverLicenseType requiredDriverLicenseType) {
        this.requiredDriverLicenseType = requiredDriverLicenseType;
    }

    public DriverLicenseType getRequiredDriverLicenseType() {
        return requiredDriverLicenseType;
    }

    @Override
    public String toString() {
        return "CarType{" +
                "requiredDriverLicenseType=" + requiredDriverLicenseType +
                '}';
    }
}
