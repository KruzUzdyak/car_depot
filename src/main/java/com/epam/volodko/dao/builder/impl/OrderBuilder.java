package com.epam.volodko.dao.builder.impl;

import com.epam.volodko.dao.CarDAO;
import com.epam.volodko.dao.DAOFactory;
import com.epam.volodko.dao.UserDAO;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.entity.car.Car;
import com.epam.volodko.entity.order.Order;
import com.epam.volodko.entity.user.Admin;
import com.epam.volodko.entity.user.Client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class OrderBuilder {

    public Order build(ResultSet resultSet) throws SQLException, DAOException {
        Order order = new Order();
        order.setOrderId(resultSet.getInt(Column.ORDERS_ORDER_ID));
        order.setDestFrom(resultSet.getString(Column.ORDERS_DEST_FROM));
        order.setDestTo(resultSet.getString(Column.ORDERS_DEST_TO));
        order.setDistance(resultSet.getInt(Column.ORDERS_DISTANCE));
        order.setDateStart(new Date(resultSet.getLong(Column.ORDERS_DATE_START)));
        order.setDateEnd(new Date(resultSet.getLong(Column.ORDERS_DATE_END)));
        order.setLoad(resultSet.getInt(Column.ORDERS_LOAD));
        order.setLoadNote(resultSet.getString(Column.ORDERS_LOAD_NOTE));
        order.setCompleted(resultSet.getBoolean(Column.ORDERS_COMPLETED));
        order.setPayment(resultSet.getInt(Column.ORDERS_PAYMENT));
        setClient(resultSet, order);
        setAdmin(resultSet, order);
        setCar(resultSet, order);
        return order;
    }

    private void setClient(ResultSet resultSet, Order order) throws SQLException, DAOException {
        int clientId = resultSet.getInt(Column.ORDERS_CLIENT_ID);
        if (clientId != 0){
            UserDAO<Client> clientDAO = DAOFactory.getInstance().getClientDAO();
            Client client = clientDAO.findById(clientId);
            order.setClient(client);
        }
    }

    private void setAdmin(ResultSet resultSet, Order order) throws SQLException, DAOException {
        int adminId = resultSet.getInt(Column.ORDERS_ADMIN_ID);
        if (adminId != 0){
            UserDAO<Admin> adminDAO = DAOFactory.getInstance().getAdminDAO();
            Admin admin = adminDAO.findById(adminId);
            order.setAdmin(admin);
        }
    }

    private void setCar(ResultSet resultSet, Order order) throws SQLException, DAOException {
        int carId = resultSet.getInt(Column.ORDERS_CAR_ID);
        if (carId != 0){
            CarDAO carDAO = DAOFactory.getInstance().getCarDAO();
            Car car = carDAO.findById(carId);
            order.setCar(car);
        }
    }
}
