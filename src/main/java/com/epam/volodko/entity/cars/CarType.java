package com.epam.volodko.entity.cars;

import com.epam.volodko.entity.users.DriverLicenseType;

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
}
