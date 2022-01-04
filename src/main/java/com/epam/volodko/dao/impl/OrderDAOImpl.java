package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.OrderDAO;
import com.epam.volodko.dao.builder.BuilderFactory;
import com.epam.volodko.dao.database.ConnectionPool;
import com.epam.volodko.dao.database.pool_exception.ConnectionPoolException;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.dao.table_name.Table;
import com.epam.volodko.entity.order.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl extends AbstractDAO implements OrderDAO {

    private static final String FIND_ORDER_BY_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s = ?;",
            Table.ORDERS, Column.ORDERS_ORDER_ID);
    private static final String FIND_ORDERS_BY_CLIENT_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s = ?;",
            Table.ORDERS, Column.ORDERS_CLIENT_ID);
    private static final String FIND_ORDERS_BY_ADMIN_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s = ?;",
            Table.ORDERS, Column.ORDERS_ADMIN_ID);
    private static final String FIND_ORDERS_BY_CAR_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s = ?;",
            Table.ORDERS, Column.ORDERS_CAR_ID);
    private static final String FIND_ALL_ORDERS_QUERY = String.format(
            "SELECT * FROM %s;", Table.ORDERS);
    private static final String SAVE_NEW_ORDER_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s, %s, %s, `%s`, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);",
            Table.ORDERS, Column.ORDERS_DEST_FROM, Column.ORDERS_DEST_TO, Column.ORDERS_DISTANCE,
            Column.ORDERS_DATE_START, Column.ORDERS_DATE_END, Column.ORDERS_LOAD, Column.ORDERS_LOAD_NOTE,
            Column.ORDERS_COMPLETED, Column.ORDERS_PAYMENT, Column.ORDERS_CLIENT_ID);
    private static final String DELETE_BY_ID_QUERY = String.format(
            "DELETE FROM %s WHERE %s = ?;",
            Table.ORDERS, Column.ORDERS_ORDER_ID);
    private static final String UPDATE_ORDER_QUERY = String.format(
            "UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, `%s` = ?, %s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ?;",
            Table.ORDERS, Column.ORDERS_DEST_FROM, Column.ORDERS_DEST_TO, Column.ORDERS_DISTANCE,
            Column.ORDERS_DATE_START, Column.ORDERS_DATE_END, Column.ORDERS_LOAD, Column.ORDERS_LOAD_NOTE,
            Column.ORDERS_COMPLETED, Column.ORDERS_PAYMENT, Column.ORDERS_CLIENT_ID, Column.ORDERS_ORDER_ID);
    private static final String SET_ADMIN_QUERY = String.format(
            "UPDATE %s SET %s = ? WHERE %s = ?;",
            Table.ORDERS, Column.ORDERS_ADMIN_ID, Column.ORDERS_ORDER_ID);
    private static final String SET_CAR_QUERY = String.format(
            "UPDATE %s SET %s = ? WHERE %s = ?;",
            Table.ORDERS, Column.ORDERS_CAR_ID, Column.ORDERS_ORDER_ID);

    @Override
    public Order findById(int id) throws DAOException {
        Order order = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(FIND_ORDER_BY_ID_QUERY);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                order = BuilderFactory.getOrderBuilder().build(resultSet);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return order;
    }

    @Override
    public List<Order> findByClientId(int clientId) throws DAOException {
        return processFindById(clientId, FIND_ORDERS_BY_CLIENT_ID_QUERY);
    }

    @Override
    public List<Order> findByAdminId(int adminId) throws DAOException {
        return processFindById(adminId, FIND_ORDERS_BY_ADMIN_ID_QUERY);
    }

    @Override
    public List<Order> findByCarId(int carId) throws DAOException {
        return processFindById(carId, FIND_ORDERS_BY_CAR_ID_QUERY);
    }

    @Override
    public List<Order> findAll() throws DAOException {
        List<Order> orders = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(FIND_ALL_ORDERS_QUERY);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = BuilderFactory.getOrderBuilder().build(resultSet);
                orders.add(order);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return orders;
    }

    @Override
    public int saveNew(Order order) throws DAOException {
        int rowsAffected;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SAVE_NEW_ORDER_QUERY);
            prepareUpdateOrderStatement(order, statement);
            rowsAffected = statement.executeUpdate();
            order.setId(getGeneratedKey(statement));
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement);
        }
        return rowsAffected;
    }

    @Override
    public int deleteById(int id) throws DAOException {
        int rowsAffected;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(DELETE_BY_ID_QUERY);
            statement.setInt(1, id);
            rowsAffected = statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement);
        }
        return rowsAffected;
    }

    @Override
    public int update(Order order) throws DAOException {
        int rowsAffected;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(UPDATE_ORDER_QUERY);
            prepareUpdateOrderStatement(order, statement);
            statement.setInt(11, order.getId());
            rowsAffected = statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement);
        }
        return rowsAffected;
    }

    @Override
    public int setAdmin(int orderId, int adminId) throws DAOException {
        return processQuery(adminId, orderId, SET_ADMIN_QUERY);
    }

    @Override
    public int setCar(int orderId, int carId) throws DAOException {
        return processQuery(carId, orderId, SET_CAR_QUERY);
    }

    private List<Order> processFindById(int id, String query) throws DAOException {
        List<Order> orders = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = BuilderFactory.getOrderBuilder().build(resultSet);
                orders.add(order);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return orders;
    }

    private void prepareUpdateOrderStatement(Order order, PreparedStatement statement) throws SQLException {
        statement.setString(1, order.getDestFrom());
        statement.setString(2, order.getDestTo());
        statement.setInt(3, order.getDistance());
        statement.setLong(4, order.getDateStart().getTime());
        statement.setLong(5, order.getDateEnd().getTime());
        statement.setInt(6, order.getLoad());
        statement.setString(7, order.getLoadNote());
        statement.setBoolean(8, order.isCompleted());
        statement.setInt(9, order.getPayment());
        statement.setInt(10, order.getClient().getId());

    }
}
