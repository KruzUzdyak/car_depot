package com.epam.volodko.entity.cars;

import com.epam.volodko.entity.users.DriverLicenseType;

public enum CarType {

    SMALL_BUS("people", DriverLicenseType.B),
    BUS("people",DriverLicenseType.D),
    SMALL_TRUCK("goods",DriverLicenseType.C),
    TRUCK("goods",DriverLicenseType.CE);

    private final String loadType;
    private final DriverLicenseType requiredDriverLicenseType;

    CarType(String loadType, DriverLicenseType requiredDriverLicenseType) {
        this.loadType = loadType;
        this.requiredDriverLicenseType = requiredDriverLicenseType;
    }

    public String getLoadType() {
        return loadType;
    }

    public DriverLicenseType getRequiredDriverLicenseType() {
        return requiredDriverLicenseType;
    }
}
