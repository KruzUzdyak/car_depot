package com.epam.volodko.entity.order;

import com.epam.volodko.entity.car.Car;
import com.epam.volodko.entity.user.Admin;
import com.epam.volodko.entity.user.Client;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Order implements Serializable {

    private int id;
    private String destFrom;
    private String destTo;
    private int distance;
    private Date dateStart;
    private Date dateEnd;
    private int load;
    private String loadNote;
    private boolean completed;
    private int payment;
    private Client client;
    private Admin admin;
    private Car car;

    public Order() {
    }

    public Order(int id, String destFrom, String destTo,
                 int distance, Date dateStart, Date dateEnd, int load,
                 String loadNote, boolean completed, int payment,
                 Client client, Admin admin, Car car) {
        this.id = id;
        this.destFrom = destFrom;
        this.destTo = destTo;
        this.distance = distance;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.load = load;
        this.loadNote = loadNote;
        this.completed = completed;
        this.payment = payment;
        this.client = client;
        this.admin = admin;
        this.car = car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
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
        return id == order.id && distance == order.distance && load == order.load &&
                completed == order.completed && payment == order.payment &&
                Objects.equals(destFrom, order.destFrom) && Objects.equals(destTo, order.destTo) &&
                Objects.equals(dateStart, order.dateStart) && Objects.equals(dateEnd, order.dateEnd) &&
                Objects.equals(loadNote, order.loadNote) && Objects.equals(client, order.client) &&
                Objects.equals(admin, order.admin) && Objects.equals(car, order.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, destFrom, destTo, distance, dateStart, dateEnd,
                load, loadNote, completed, payment, client, admin, car);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + id +
                ", destFrom='" + destFrom + '\'' +
                ", destTo='" + destTo + '\'' +
                ", distance=" + distance +
                ", dateStart=" + dateStart +
                ", dateFinish=" + dateEnd +
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
