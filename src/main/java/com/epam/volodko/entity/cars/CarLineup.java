package com.epam.volodko.entity.cars;

import java.util.HashMap;
import java.util.Map;

public class CarLineup {

    private final Map<Integer, Car> cars;

    private CarLineup(){
        cars = new HashMap<>();
    }

    public void addCar(Car car){
        cars.put(car.getCarId() ,car);
    }

    public Car getCar(int carId){
        return cars.get(carId);
    }
}
