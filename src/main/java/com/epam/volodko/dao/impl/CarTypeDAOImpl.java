package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.CarTypeDAO;
import com.epam.volodko.dao.builder.BuilderFactory;
import com.epam.volodko.dao.database.ConnectionPool;
import com.epam.volodko.dao.database.pool_exception.ConnectionPoolException;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.dao.table_name.Table;
import com.epam.volodko.entity.car.CarType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarTypeDAOImpl extends AbstractDAO implements CarTypeDAO {

    private static final String FIND_CAR_TYPE_BY_ID_QUERY = String.format(
            "SELECT * FROM %s AS ct JOIN %s AS lt ON ct.%s = lt.%s WHERE ct.%s = ?;",
            Table.CAR_TYPES, Table.LICENSE_TYPES, Column.CAR_TYPES_REQUIRED_LICENSE_ID,
            Column.LICENSE_ID, Column.CAR_TYPES_ID);
    private static final String FIND_ALL_CAR_TYPES_QUERY = String.format(
            "SELECT * FROM %s AS ct JOIN %s AS lt ON ct.%s = lt.%s;",
            Table.CAR_TYPES, Table.LICENSE_TYPES, Column.CAR_TYPES_REQUIRED_LICENSE_ID,
            Column.LICENSE_ID);
    private static final String SAVE_NEW_CAR_TYPE_QUERY = String.format(
            "INSERT INTO %s (%s, %s) VALUES (?, ?);",
            Table.CAR_TYPES, Column.CAR_TYPES_NAME, Column.CAR_TYPES_REQUIRED_LICENSE_ID);
    private static final String GET_SAVED_CAR_TYPE_ID_QUERY = String.format(
            "SELECT %s FROM %s WHERE %s = ? AND %s = ?;",
            Column.CAR_TYPES_ID, Table.CAR_TYPES, Column.CAR_TYPES_NAME, Column.CAR_TYPES_REQUIRED_LICENSE_ID);
    private static final String DELETE_CAR_TYPE_BY_ID_QUERY = String.format(
            "DELETE FROM %s WHERE %s = ?;",
            Table.CAR_TYPES, Column.CAR_TYPES_ID);
    private static final String UPDATE_CAR_TYPE_QUERY = String.format(
            "UPDATE %s SET %s = ?, %s = ? WHERE %s = ?",
            Table.CAR_TYPES, Column.CAR_TYPES_NAME, Column.CAR_TYPES_REQUIRED_LICENSE_ID, Column.CAR_TYPES_ID);

    @Override
    public CarType findById(int carTypeId) throws DAOException {
        CarType carType;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(FIND_CAR_TYPE_BY_ID_QUERY);
            statement.setInt(1, carTypeId);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                carType = BuilderFactory.getCarTypeBuilder().build(resultSet);
            } else {
                carType = new CarType();
            }
        } catch (SQLException e) {
            throw new DAOException("SQLException when try to find car type by id.", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return carType;
    }

    @Override
    public List<CarType> findAll() throws DAOException {
        List<CarType> carTypes = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(FIND_ALL_CAR_TYPES_QUERY);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                CarType carType = BuilderFactory.getCarTypeBuilder().build(resultSet);
                carTypes.add(carType);
            }
        } catch (SQLException e) {
            throw new DAOException("SQLException when try to find all car types.", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return carTypes;
    }

    @Override
    public int saveNew(CarType carType) throws DAOException {
        int rowsAffected;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SAVE_NEW_CAR_TYPE_QUERY);
            statement.setString(1, carType.getTypeName());
            statement.setInt(2, carType.getRequiredLicense().getId());
            rowsAffected = statement.executeUpdate();
            statement = connection.prepareStatement(GET_SAVED_CAR_TYPE_ID_QUERY);
            statement.setString(1, carType.getTypeName());
            statement.setInt(2, carType.getRequiredLicense().getId());
            resultSet = statement.executeQuery();
            connection.commit();
            if (resultSet.next()) {
                carType.setCarTypeId(resultSet.getInt(Column.CAR_TYPES_ID));
            }
        } catch (SQLException e) {
            throw new DAOException("SQLException when try to save new car type.", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return rowsAffected;
    }

    @Override
    public int deleteById(int carTypeId) throws DAOException {
        int rowsAffected;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(DELETE_CAR_TYPE_BY_ID_QUERY);
            statement.setInt(1, carTypeId);
            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQLException when try to delete car type.", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement);
        }
        return rowsAffected;
    }

    @Override
    public int update(CarType carType) throws DAOException {
        int rowsAffected;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(UPDATE_CAR_TYPE_QUERY);
            statement.setString(1, carType.getTypeName());
            statement.setInt(2, carType.getRequiredLicense().getId());
            statement.setInt(3, carType.getCarTypeId());
            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQLException when try to update car type.", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement);
        }
        return rowsAffected;
    }
}