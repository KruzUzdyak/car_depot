package com.epam.volodko.entity.car;

import com.epam.volodko.entity.user.DriverLicenseType;

import java.util.Objects;

public class CarType {

    private int carTypeId;
    private String typeName;
    private DriverLicenseType requiredLicense;

    public CarType() {
    }

    public CarType(int carTypeId, String typeName, DriverLicenseType requiredLicense) {
        this.carTypeId = carTypeId;
        this.typeName = typeName;
        this.requiredLicense = requiredLicense;
    }

    public DriverLicenseType getRequiredLicense() {
        return requiredLicense;
    }

    public int getCarTypeId() {
        return carTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setCarTypeId(int carTypeId) {
        this.carTypeId = carTypeId;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setRequiredLicense(DriverLicenseType requiredLicense) {
        this.requiredLicense = requiredLicense;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarType carType = (CarType) o;
        return carTypeId == carType.carTypeId && Objects.equals(typeName, carType.typeName) &&
                requiredLicense == carType.requiredLicense;
    }

    @Override
    public int hashCode() {
        return Objects.hash(carTypeId, typeName, requiredLicense);
    }

    @Override
    public String toString() {
        return "CarType{" +
                "carTypeId=" + carTypeId +
                ", typeName='" + typeName + '\'' +
                ", requiredLicense=" + requiredLicense +
                '}';
    }
}
