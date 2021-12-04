package com.epam.volodko.entity.car;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class RefuelRecord implements Serializable {

    private int id;
    private Date refuelDate;
    private int fuelPrice;
    private int refuelAmount;
    private Car car;

    public RefuelRecord() {
    }

    public RefuelRecord(int id, Date refuelDate, int fuelPrice,
                        int refuelAmount, Car car) {
        this.id = id;
        this.refuelDate = refuelDate;
        this.fuelPrice = fuelPrice;
        this.refuelAmount = refuelAmount;
        this.car = car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getRefuelDate() {
        return refuelDate;
    }

    public void setRefuelDate(Date refuelDate) {
        this.refuelDate = refuelDate;
    }

    public int getFuelPrice() {
        return fuelPrice;
    }

    public void setFuelPrice(int fuelPrice) {
        this.fuelPrice = fuelPrice;
    }

    public int getRefuelAmount() {
        return refuelAmount;
    }

    public void setRefuelAmount(int refuelAmount) {
        this.refuelAmount = refuelAmount;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RefuelRecord that = (RefuelRecord) o;
        return id == that.id && fuelPrice == that.fuelPrice && refuelAmount == that.refuelAmount &&
                Objects.equals(refuelDate, that.refuelDate) && Objects.equals(car, that.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, refuelDate, fuelPrice, refuelAmount, car);
    }

    @Override
    public String toString() {
        return "RefuelRecord{" +
                "id=" + id +
                ", refuelDate=" + refuelDate +
                ", fuelPrice=" + fuelPrice +
                ", refuelAmount=" + refuelAmount +
                ", car=" + car +
                '}';
    }
}
