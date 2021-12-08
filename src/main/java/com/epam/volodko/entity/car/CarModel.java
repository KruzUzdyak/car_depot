package com.epam.volodko.entity.car;

import java.io.Serializable;
import java.util.Objects;

public class CarModel implements Serializable {

    private int carModelId;
    private String modelName;
    private int capacity;
    private int fuelTank;
    private CarType type;
    private String loadType;

    public CarModel() {
    }

    public CarModel(int carModelId, String modelName, int capacity, int fuelTank, String loadType, CarType type) {
        this.carModelId = carModelId;
        this.modelName = modelName;
        this.capacity = capacity;
        this.fuelTank = fuelTank;
        this.type = type;
        this.loadType = loadType;
    }

    public int getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(int carModelId) {
        this.carModelId = carModelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getFuelTank() {
        return fuelTank;
    }

    public void setFuelTank(int fuelTank) {
        this.fuelTank = fuelTank;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public String getLoadType() {
        return loadType;
    }

    public void setLoadType(String loadType) {
        this.loadType = loadType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarModel carModel = (CarModel) o;
        return carModelId == carModel.carModelId && capacity == carModel.capacity &&
                fuelTank == carModel.fuelTank && Objects.equals(modelName, carModel.modelName) &&
                type == carModel.type && Objects.equals(loadType, carModel.loadType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carModelId, modelName, capacity, fuelTank, type, loadType);
    }

    @Override
    public String toString() {
        return "CarModel{" +
                "carModelId=" + carModelId +
                ", modelName='" + modelName + '\'' +
                ", capacity=" + capacity +
                ", fuelTank=" + fuelTank +
                ", type=" + type +
                ", loadType='" + loadType + '\'' +
                '}';
    }
}
