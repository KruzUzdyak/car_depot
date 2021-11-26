package com.epam.volodko.entity.cars;

public class CarModel {

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
}
