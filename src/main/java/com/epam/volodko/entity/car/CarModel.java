package com.epam.volodko.entity.car;

import java.io.Serializable;
import java.util.Objects;

public class CarModel implements Serializable {

    private final int carModelId;
    private final String modelName;
    private final int capacity;
    private final int fuelTank;
    private final CarType type;
    private final String loadType;

    public CarModel(int carModelId, String modelName, int capacity, int fuelTank, CarType type, String loadType) {
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

    public String getModelName() {
        return modelName;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFuelTank() {
        return fuelTank;
    }

    public CarType getCarType() {
        return type;
    }

    public String getLoadType() {
        return loadType;
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
