package com.epam.volodko.entity.cars;

import com.epam.volodko.entity.users.Driver;

public class Car {

    private final int carId;
    private String plateNumber;
    private int fuelLevel;
    private int mileage;
    private boolean isBroken;
    private final CarModel model;
    private Driver driver;

    public Car(int carId, String plateNumber, int fuelLevel,
               int mileage, boolean isBroken, CarModel model, Driver driver) {
        this.carId = carId;
        this.plateNumber = plateNumber;
        this.fuelLevel = fuelLevel;
        this.mileage = mileage;
        this.isBroken = isBroken;
        this.model = model;
        this.driver = driver;
    }

    public int getCarId() {
        return carId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public int getFuelLevel() {
        return fuelLevel;
    }

    public int getMileage() {
        return mileage;
    }

    public boolean isBroken() {
        return isBroken;
    }

    public CarModel getModel() {
        return model;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public void setFuelLevel(int fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public void setBroken(boolean broken) {
        isBroken = broken;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
