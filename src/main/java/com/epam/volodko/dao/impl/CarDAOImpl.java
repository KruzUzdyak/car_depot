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
                    JOIN %s u on c.%s = u.%s
                    JOIN %s r on u.%s = r.%s
                    RIGHT JOIN %s dl on u.%s = dl.%s
                    JOIN %s lt on dl.%s = lt.%s
                    WHERE c.%s = ?;""",
            Table.CARS,
            Table.CAR_MODELS, Column.CARS_MODEL_ID, Column.CAR_MODELS_ID,
            Table.CAR_TYPES, Column.CAR_MODELS_TYPE_ID, Column.CAR_TYPES_ID,
            Table.USERS, Column.CARS_DRIVER_ID, Column.USERS_ID,
            Table.ROLES, Column.USERS_ROLE_ID, Column.ROLES_ID,
            Table.DRIVER_LICENSES, Column.USERS_ID, Column.DRIVER_LICENSES_USER_ID,
            Table.LICENSE_TYPES, Column.DRIVER_LICENSES_LICENSE_ID, Column.LICENSE_ID,
            Column.CARS_ID);
    private static final String FIND_CAR_BY_DRIVER_QUERY = String.format("""
                    SELECT * FROM %s c
                    JOIN %s cm ON c.%s = cm.%s
                    JOIN %s ct ON cm.%s = ct.%s
                    JOIN %s u on c.%s = u.%s
                    JOIN %s r on u.%s = r.%s
                    RIGHT JOIN %s dl on u.%s = dl.%s
                    JOIN %s lt on dl.%s = lt.%s
                    WHERE c.%s = ?;""",
            Table.CARS,
            Table.CAR_MODELS, Column.CARS_MODEL_ID, Column.CAR_MODELS_ID,
            Table.CAR_TYPES, Column.CAR_MODELS_TYPE_ID, Column.CAR_TYPES_ID,
            Table.USERS, Column.CARS_DRIVER_ID, Column.USERS_ID,
            Table.ROLES, Column.USERS_ROLE_ID, Column.ROLES_ID,
            Table.DRIVER_LICENSES, Column.USERS_ID, Column.DRIVER_LICENSES_USER_ID,
            Table.LICENSE_TYPES, Column.DRIVER_LICENSES_LICENSE_ID, Column.LICENSE_ID,
            Column.CARS_DRIVER_ID);
    private static final String FIND_ALL_CARS_QUERY = String.format("""
                    SELECT * FROM %s c
                    JOIN %s cm ON c.%s = cm.%s
                    JOIN %s ct ON cm.%s = ct.%s
                    JOIN %s u on c.%s = u.%s
                    JOIN %s r on u.%s = r.%s
                    RIGHT JOIN %s dl on u.%s = dl.%s
                    JOIN %s lt on dl.%s = lt.%s;""",
            Table.CARS,
            Table.CAR_MODELS, Column.CARS_MODEL_ID, Column.CAR_MODELS_ID,
            Table.CAR_TYPES, Column.CAR_MODELS_TYPE_ID, Column.CAR_TYPES_ID,
            Table.USERS, Column.CARS_DRIVER_ID, Column.USERS_ID,
            Table.ROLES, Column.USERS_ROLE_ID, Column.ROLES_ID,
            Table.DRIVER_LICENSES, Column.USERS_ID, Column.DRIVER_LICENSES_USER_ID,
            Table.LICENSE_TYPES, Column.DRIVER_LICENSES_LICENSE_ID, Column.LICENSE_ID);

    @Override
    public Car findCarById(int carId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Car car;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(FIND_CAR_BY_ID_QUERY);
            statement.setInt(1, carId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                car = BuilderFactory.getCarBuilder().build(resultSet);
            } else {
                car = new Car();
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException("SQLException when try to find car by id.", e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return car;
    }

    @Override
    public Car findCarByDriver(Driver driver) throws DAOException {
        Car car;
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
            } else {
                car = new Car();
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException("SQLException when try to find car by driver.", e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return car;
    }

    @Override
    public List<Car> findAllCars() throws DAOException {
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
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException("SQLException when try to find all cars.", e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return cars;
    }

    @Override
    public int saveNewCar(Car car) throws DAOException {
        return 0;
    }

    @Override
    public int deleteCar(Car car) throws DAOException {
        return 0;
    }

    @Override
    public int updateCar(Car car) throws DAOException {
        return 0;
    }



}
