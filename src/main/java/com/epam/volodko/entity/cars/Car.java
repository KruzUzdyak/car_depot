package com.epam.volodko.entity.cars;

import com.epam.volodko.entity.users.Driver;

import java.util.Objects;

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

    public int getFuelTankCapacity(){
        return model.getFuelTank();
    }

    public int getCargoCapacity(){
        return model.getCapacity();
    }

    public String getLoadType(){
        return model.getLoadType();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return carId == car.carId && fuelLevel == car.fuelLevel && mileage == car.mileage &&
                isBroken == car.isBroken && Objects.equals(plateNumber, car.plateNumber) &&
                Objects.equals(model, car.model) && Objects.equals(driver, car.driver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, plateNumber, fuelLevel, mileage, isBroken, model, driver);
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", plateNumber='" + plateNumber + '\'' +
                ", fuelLevel=" + fuelLevel +
                ", mileage=" + mileage +
                ", isBroken=" + isBroken +
                ", model=" + model +
                ", driver=" + driver +
                '}';
    }
}
