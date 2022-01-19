package com.epam.volodko.entity.car;

import com.epam.volodko.entity.user.Driver;

import java.io.Serializable;
import java.util.Objects;

public class Car implements Serializable {

    private int id;
    private String plateNumber;
    private int fuelLevel;
    private int mileage;
    private boolean broken;
    private CarModel model;
    private Driver driver;

    public Car(){
    }

    public Car(int id, String plateNumber, int fuelLevel,
               int mileage, boolean broken, CarModel model, Driver driver) {
        this.id = id;
        this.plateNumber = plateNumber;
        this.fuelLevel = fuelLevel;
        this.mileage = mileage;
        this.broken = broken;
        this.model = model;
        this.driver = driver;
    }

    public int getId() {
        return id;
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
        return broken;
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
        this.broken = broken;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModel(CarModel model) {
        this.model = model;
    }

    public boolean hasDriver(){
        return driver != null;
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
        return id == car.id && fuelLevel == car.fuelLevel && mileage == car.mileage &&
                broken == car.broken && Objects.equals(plateNumber, car.plateNumber) &&
                Objects.equals(model, car.model) && Objects.equals(driver, car.driver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, plateNumber, fuelLevel, mileage, broken, model, driver);
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + id +
                ", plateNumber='" + plateNumber + '\'' +
                ", fuelLevel=" + fuelLevel +
                ", mileage=" + mileage +
                ", isBroken=" + broken +
                ", model=" + model +
                ", driver=" + driver +
                '}';
    }
}
