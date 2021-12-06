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
import java.util.List;

public class CarDAOImpl implements CarDAO {

    private static final String FIND_CAR_BY_ID_QUERY = String.format(
            "SELECT * FROM %s AS c JOIN %s AS cm ON c.%s = cm.%s JOIN %s AS ct ON cm.%s = ct.%s " +
                    "JOIN %s AS lt ON ct.%s = lt.%s WHERE %s = ?;",
            Table.CARS, Table.CAR_MODELS, Column.CARS_MODEL_ID, Column.CAR_MODELS_ID, Table.CAR_TYPES,
            Column.CAR_MODELS_TYPE_ID, Column.CAR_TYPES_ID, Table.LICENSE_TYPES,
            Column.CAR_TYPES_REQUIRED_LICENSE, Column.LICENSE_ID, Column.CARS_ID);

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
            if (resultSet.next()){
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
        return null;
    }

    @Override
    public List<Car> findAllCars() throws DAOException {
        return null;
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

    private void closeConnection(Connection connection, Statement statement, ResultSet resultSet) throws DAOException {
        try {
            ConnectionPool.getInstance().closeConnection(connection, statement, resultSet);
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }

    private void closeConnection(Connection connection, Statement statement) throws DAOException{
        try {
            ConnectionPool.getInstance().closeConnection(connection, statement);
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }

}
