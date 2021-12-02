package com.epam.volodko.entity.car;

import com.epam.volodko.entity.user.DriverLicenseType;

public enum CarType {

    SMALL_BUS(1, DriverLicenseType.B),
    BUS(2, DriverLicenseType.D),
    SMALL_TRUCK(3, DriverLicenseType.C),
    TRUCK_WITH_TRAILER(4, DriverLicenseType.CE);

    private final int carTypeId;
    private final DriverLicenseType requiredDriverLicenseType;

    CarType(int carTypeId, DriverLicenseType requiredDriverLicenseType) {
        this.carTypeId = carTypeId;
        this.requiredDriverLicenseType = requiredDriverLicenseType;
    }

    public DriverLicenseType getRequiredDriverLicenseType() {
        return requiredDriverLicenseType;
    }

    public int getCarTypeId() {
        return carTypeId;
    }

    @Override
    public String toString() {
        return "CarType{" +
                "carTypeId=" + carTypeId +
                ", requiredDriverLicenseType=" + requiredDriverLicenseType +
                '}';
    }

}
