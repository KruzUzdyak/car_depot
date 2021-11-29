package com.epam.volodko.entity.order;

import com.epam.volodko.entity.car.Car;
import com.epam.volodko.entity.user.Admin;
import com.epam.volodko.entity.user.Client;

import java.text.SimpleDateFormat;
import java.util.Objects;

public class Order {

    private int orderId;
    private String destFrom;
    private String destTo;
    private int distance;
    private SimpleDateFormat dateStart;
    private SimpleDateFormat dateFinish;
    private int load;
    private String loadNote;
    private boolean completed;
    private int payment;
    private Client client;
    private Admin admin;
    private Car car;

    public Order() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getDestFrom() {
        return destFrom;
    }

    public void setDestFrom(String destFrom) {
        this.destFrom = destFrom;
    }

    public String getDestTo() {
        return destTo;
    }

    public void setDestTo(String destTo) {
        this.destTo = destTo;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public SimpleDateFormat getDateStart() {
        return dateStart;
    }

    public void setDateStart(SimpleDateFormat dateStart) {
        this.dateStart = dateStart;
    }

    public SimpleDateFormat getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(SimpleDateFormat dateFinish) {
        this.dateFinish = dateFinish;
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }

    public String getLoadNote() {
        return loadNote;
    }

    public void setLoadNote(String loadNote) {
        this.loadNote = loadNote;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
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
        Order order = (Order) o;
        return orderId == order.orderId && distance == order.distance && load == order.load &&
                completed == order.completed && payment == order.payment &&
                Objects.equals(destFrom, order.destFrom) && Objects.equals(destTo, order.destTo) &&
                Objects.equals(dateStart, order.dateStart) && Objects.equals(dateFinish, order.dateFinish) &&
                Objects.equals(loadNote, order.loadNote) && Objects.equals(client, order.client) &&
                Objects.equals(admin, order.admin) && Objects.equals(car, order.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, destFrom, destTo, distance, dateStart, dateFinish,
                load, loadNote, completed, payment, client, admin, car);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", destFrom='" + destFrom + '\'' +
                ", destTo='" + destTo + '\'' +
                ", distance=" + distance +
                ", dateStart=" + dateStart +
                ", dateFinish=" + dateFinish +
                ", load=" + load +
                ", loadNote='" + loadNote + '\'' +
                ", completed=" + completed +
                ", payment=" + payment +
                ", client=" + client +
                ", admin=" + admin +
                ", car=" + car +
                '}';
    }
}
