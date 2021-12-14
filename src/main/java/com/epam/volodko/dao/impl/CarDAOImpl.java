package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.CarDAO;
import com.epam.volodko.dao.builder.BuilderFactory;
import com.epam.volodko.dao.database.ConnectionPool;
import com.epam.volodko.dao.database.pool_exception.ConnectionPoolException;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.dao.table_name.Table;
import com.epam.volodko.entity.car.Car;
import com.epam.volodko.entity.user.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAOImpl extends AbstractDAO implements CarDAO {

    private static final String FIND_CAR_BY_ID_QUERY = String.format("""
                    SELECT * FROM %s c
                    JOIN %s cm ON c.%s = cm.%s
                    JOIN %s ct ON cm.%s = ct.%s
                    JOIN %s lt ON ct.%s = lt.%s
                    WHERE c.%s = ?;""",
            Table.CARS,
            Table.CAR_MODELS, Column.CARS_MODEL_ID, Column.CAR_MODELS_ID,
            Table.CAR_TYPES, Column.CAR_MODELS_TYPE_ID, Column.CAR_TYPES_ID,
            Table.LICENSE_TYPES, Column.CAR_TYPES_REQUIRED_LICENSE_ID, Column.LICENSE_ID,
            Column.CARS_ID);
    private static final String FIND_CAR_BY_DRIVER_QUERY = String.format("""
                    SELECT * FROM %s c
                    JOIN %s cm ON c.%s = cm.%s
                    JOIN %s ct ON cm.%s = ct.%s
                    JOIN %s lt on ct.%s = lt.%s
                    WHERE c.%s = ?;""",
            Table.CARS,
            Table.CAR_MODELS, Column.CARS_MODEL_ID, Column.CAR_MODELS_ID,
            Table.CAR_TYPES, Column.CAR_MODELS_TYPE_ID, Column.CAR_TYPES_ID,
            Table.LICENSE_TYPES, Column.CAR_TYPES_REQUIRED_LICENSE_ID, Column.LICENSE_ID,
            Column.CARS_DRIVER_ID);
    private static final String FIND_ALL_CARS_QUERY = String.format("""
                    SELECT * FROM %s c
                    JOIN %s cm ON c.%s = cm.%s
                    JOIN %s ct ON cm.%s = ct.%s
                    JOIN %s lt on ct.%s = lt.%s;""",
            Table.CARS,
            Table.CAR_MODELS, Column.CARS_MODEL_ID, Column.CAR_MODELS_ID,
            Table.CAR_TYPES, Column.CAR_MODELS_TYPE_ID, Column.CAR_TYPES_ID,
            Table.LICENSE_TYPES, Column.CAR_TYPES_REQUIRED_LICENSE_ID, Column.LICENSE_ID);
    private static final String SAVE_NEW_CAR_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?);",
            Table.CARS, Column.CARS_PLATE_NUMBER, Column.CARS_FUEL_LEVEL, Column.CARS_MILEAGE,
            Column.CARS_BROKEN, Column.CARS_MODEL_ID, Column.CARS_DRIVER_ID);
    private static final String DELETE_CAR_QUERY = String.format(
            "DELETE FROM %s WHERE %s = ?;",
            Table.CARS, Column.CARS_ID);
    private static final String UPDATE_CAR_QUERY = String.format(
            "UPDATE %s SET %s=?, %s=?, %s=?, %s=?, %s=? %s=? WHERE %s=?;",
            Table.CARS, Column.CARS_PLATE_NUMBER, Column.CARS_FUEL_LEVEL, Column.CARS_MILEAGE,
            Column.CARS_BROKEN, Column.CARS_MODEL_ID, Column.CARS_DRIVER_ID, Column.CARS_ID);

    @Override
    public Car findById(int carId) throws DAOException {
        Car car = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(FIND_CAR_BY_ID_QUERY);
            statement.setInt(1, carId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                car = BuilderFactory.getCarBuilder().build(resultSet);
                System.out.println("res.next");
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return car;
    }

    @Override
    public Car findByDriver(Driver driver) throws DAOException {
        Car car = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(FIND_CAR_BY_DRIVER_QUERY);
            statement.setInt(1, driver.getId());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                 car = BuilderFactory.getCarBuilder().build(resultSet);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return car;
    }

    @Override
    public List<Car> findAll() throws DAOException {
        List<Car> cars = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(FIND_ALL_CARS_QUERY,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                Car car = BuilderFactory.getCarBuilder().build(resultSet);
                cars.add(car);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return cars;
    }

    @Override
    public int saveNew(Car car) throws DAOException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SAVE_NEW_CAR_QUERY);
            prepareUpdateCarStatement(car, statement);
            rowsAffected = statement.executeUpdate();
            car.setId(getGeneratedKey(statement));
        } catch (ConnectionPoolException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection, statement);
        }
        return rowsAffected;
    }

    @Override
    public int delete(Car car) throws DAOException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(DELETE_CAR_QUERY);
            statement.setInt(1, car.getId());
            rowsAffected = statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection, statement);
        }
        return rowsAffected;
    }

    @Override
    public int update(Car car) throws DAOException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(UPDATE_CAR_QUERY);
            prepareUpdateCarStatement(car, statement);
            statement.setInt(7, car.getId());
            rowsAffected = statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection, statement);
        }
        return rowsAffected;
    }

    private void prepareUpdateCarStatement(Car car, PreparedStatement statement) throws SQLException {
        statement.setString(1, car.getPlateNumber());
        statement.setInt(2, car.getFuelLevel());
        statement.setInt(3, car.getMileage());
        statement.setBoolean(4, car.isBroken());
        statement.setInt(5, car.getModel().getId());
        statement.setInt(6, car.getDriver().getId());
    }


}
