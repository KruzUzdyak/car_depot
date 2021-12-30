package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.CarModelDAO;
import com.epam.volodko.dao.builder.BuilderFactory;
import com.epam.volodko.dao.database.ConnectionPool;
import com.epam.volodko.dao.database.pool_exception.ConnectionPoolException;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.dao.table_name.Table;
import com.epam.volodko.entity.car.CarModel;
import com.epam.volodko.entity.car.CarType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarModelDAOImpl extends AbstractDAO implements CarModelDAO {

    private static final String GET_CAR_MODEL_BY_ID_QUERY = String.format(
            "SELECT * FROM %s AS cm JOIN %s AS ct ON cm.%s = ct.%s JOIN %s AS lt ON ct.%s = lt.%s WHERE %s = ?;",
            Table.CAR_MODELS, Table.CAR_TYPES, Column.CAR_MODELS_TYPE_ID, Column.CAR_TYPES_ID,
            Table.LICENSE_TYPES, Column.CAR_TYPES_REQUIRED_LICENSE_ID, Column.LICENSE_ID ,Column.CAR_MODELS_ID);
    private static final String GET_CAR_MODELS_BY_CAR_TYPE_QUERY = String.format(
            "SELECT * FROM %s AS cm JOIN %s AS ct ON cm.%s = ct.%s JOIN %s AS lt ON ct.%s = lt.%s WHERE %s = ?;",
            Table.CAR_MODELS, Table.CAR_TYPES, Column.CAR_MODELS_TYPE_ID, Column.CAR_TYPES_ID,
            Table.LICENSE_TYPES, Column.CAR_TYPES_REQUIRED_LICENSE_ID, Column.LICENSE_ID ,Column.CAR_MODELS_TYPE_ID);
    private static final String GET_ALL_CAR_MODELS_QUERY = String.format(
            "SELECT * FROM %s AS cm JOIN %s AS ct ON cm.%s = ct.%s JOIN %s AS lt ON ct.%s = lt.%s;",
            Table.CAR_MODELS, Table.CAR_TYPES, Column.CAR_MODELS_TYPE_ID, Column.CAR_TYPES_ID,
            Table.LICENSE_TYPES, Column.CAR_TYPES_REQUIRED_LICENSE_ID, Column.LICENSE_ID);
    private static final String SAVE_NEW_CAR_MODEL_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?);",
            Table.CAR_MODELS, Column.CAR_MODELS_NAME, Column.CAR_MODELS_CAPACITY, Column.CAR_MODELS_LOAD_TYPE,
            Column.CAR_MODELS_FUEL_TANK, Column.CAR_MODELS_TYPE_ID);
    private static final String GET_SAVED_CAR_MODEL_ID_QUERY = String.format(
            "SELECT %s FROM %s WHERE %s = ?;",
            Column.CAR_MODELS_ID, Table.CAR_MODELS, Column.CAR_MODELS_NAME);
    private static final String DELETE_CAR_MODEL_BY_ID_QUERY = String.format(
            "DELETE FROM %s WHERE %s = ?;",
            Table.CAR_MODELS, Column.CAR_MODELS_ID);
    private static final String UPDATE_CAR_MODEL_QUERY = String.format(
            "UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ?;",
            Table.CAR_MODELS, Column.CAR_MODELS_NAME, Column.CAR_MODELS_CAPACITY, Column.CAR_MODELS_LOAD_TYPE,
            Column.CAR_MODELS_FUEL_TANK, Column.CAR_MODELS_TYPE_ID, Column.CAR_MODELS_ID);

    @Override
    public CarModel findById(int carModelId) throws DAOException {
        CarModel carModel;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(GET_CAR_MODEL_BY_ID_QUERY);
            statement.setInt(1, carModelId);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                carModel = BuilderFactory.getModelBuilder().build(resultSet);
            } else {
                carModel = new CarModel();
            }
        } catch (SQLException e) {
            throw new DAOException("SQLException when try to find car model by id.", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return carModel;
    }

    @Override
    public List<CarModel> findByCarType(CarType carType) throws DAOException {
        List<CarModel> carModels = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(GET_CAR_MODELS_BY_CAR_TYPE_QUERY);
            statement.setInt(1, carType.getCarTypeId());
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                CarModel carModel = BuilderFactory.getModelBuilder().build(resultSet);
                carModels.add(carModel);
            }
        } catch (ConnectionPoolException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return carModels;
    }

    @Override
    public List<CarModel> findAll() throws DAOException {
        List<CarModel> carModels = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(GET_ALL_CAR_MODELS_QUERY);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                CarModel carModel = BuilderFactory.getModelBuilder().build(resultSet);
                carModels.add(carModel);
            }
        } catch (ConnectionPoolException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return carModels;
    }

    @Override
    public int saveNew(CarModel carModel) throws DAOException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SAVE_NEW_CAR_MODEL_QUERY,
                    Statement.RETURN_GENERATED_KEYS);
            prepareCarModelStatement(carModel, statement);
            rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DAOException("Saving new car model failed, no rows affected.");
            }
        carModel.setId(getGeneratedKey(statement));
        } catch (ConnectionPoolException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection, statement);
        }
        return rowsAffected;
    }

    @Override
    public int deleteById(int carModelId) throws DAOException {
        return deleteById(carModelId, DELETE_CAR_MODEL_BY_ID_QUERY);
    }

    @Override
    public int update(CarModel carModel) throws DAOException {
        int rowsAffected;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(UPDATE_CAR_MODEL_QUERY);
            prepareCarModelStatement(carModel, statement);
            statement.setInt(6, carModel.getId());
            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQLException when try to update car model.", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement);
        }
        return rowsAffected;
    }

    private void prepareCarModelStatement(CarModel carModel, PreparedStatement statement) throws SQLException {
        statement.setString(1, carModel.getModelName());
        statement.setInt(2, carModel.getCapacity());
        statement.setString(3, carModel.getLoadType());
        statement.setInt(4, carModel.getFuelTank());
        statement.setInt(5, carModel.getType().getCarTypeId());
    }
}
