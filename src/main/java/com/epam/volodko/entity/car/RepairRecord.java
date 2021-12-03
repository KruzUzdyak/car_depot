package com.epam.volodko.entity.car;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class RepairRecord implements Serializable {

    private int id;
    private SimpleDateFormat repairStart;
    private SimpleDateFormat repairEnd;
    private int expenses;
    private Car car;

    public RepairRecord() {
    }

    public RepairRecord(int id, SimpleDateFormat repairStart,
                        SimpleDateFormat repairEnd, int expenses, Car car) {
        this.id = id;
        this.repairStart = repairStart;
        this.repairEnd = repairEnd;
        this.expenses = expenses;
        this.car = car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SimpleDateFormat getRepairStart() {
        return repairStart;
    }

    public void setRepairStart(SimpleDateFormat repairStart) {
        this.repairStart = repairStart;
    }

    public SimpleDateFormat getRepairEnd() {
        return repairEnd;
    }

    public void setRepairEnd(SimpleDateFormat repairEnd) {
        this.repairEnd = repairEnd;
    }

    public int getExpenses() {
        return expenses;
    }

    public void setExpenses(int expenses) {
        this.expenses = expenses;
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
        RepairRecord that = (RepairRecord) o;
        return id == that.id && expenses == that.expenses && Objects.equals(repairStart, that.repairStart) && Objects.equals(repairEnd, that.repairEnd) && Objects.equals(car, that.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, repairStart, repairEnd, expenses, car);
    }

    @Override
    public String toString() {
        return "RepairRecord{" +
                "id=" + id +
                ", repairStart=" + repairStart +
                ", repairEnd=" + repairEnd +
                ", expenses=" + expenses +
                ", car=" + car +
                '}';
    }
}
