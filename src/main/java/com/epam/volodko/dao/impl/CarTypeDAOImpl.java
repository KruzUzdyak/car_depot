package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.CarTypeDAO;
import com.epam.volodko.dao.builder.BuilderFactory;
import com.epam.volodko.dao.database.ConnectionPool;
import com.epam.volodko.dao.database.pool_exception.ConnectionPoolException;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.dao.table_name.Table;
import com.epam.volodko.entity.car.CarType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarTypeDAOImpl extends AbstractDAO implements CarTypeDAO {

    private static final String FIND_CAR_TYPE_BY_ID_QUERY = String.format(
            "SELECT * FROM %s AS ct JOIN %s AS lt ON ct.%s = lt.%s WHERE ct.%s = ?;",
            Table.CAR_TYPES, Table.LICENSE_TYPES,
            Column.CAR_TYPES_REQUIRED_LICENSE_ID, Column.LICENSE_ID, Column.CAR_TYPES_ID);
    private static final String FIND_ALL_CAR_TYPES_QUERY = String.format(
            "SELECT * FROM %s AS ct JOIN %s AS lt ON ct.%s = lt.%s;",
            Table.CAR_TYPES, Table.LICENSE_TYPES,
            Column.CAR_TYPES_REQUIRED_LICENSE_ID, Column.LICENSE_ID);
    private static final String SAVE_NEW_CAR_TYPE_QUERY = String.format(
            "INSERT INTO %s (%s, %s) VALUES (?, ?);",
            Table.CAR_TYPES, Column.CAR_TYPES_NAME, Column.CAR_TYPES_REQUIRED_LICENSE_ID);
    private static final String DELETE_CAR_TYPE_BY_ID_QUERY = String.format(
            "DELETE FROM %s WHERE %s = ?;",
            Table.CAR_TYPES, Column.CAR_TYPES_ID);
    private static final String UPDATE_CAR_TYPE_QUERY = String.format(
            "UPDATE %s SET %s = ?, %s = ? WHERE %s = ?",
            Table.CAR_TYPES,
            Column.CAR_TYPES_NAME, Column.CAR_TYPES_REQUIRED_LICENSE_ID, Column.CAR_TYPES_ID);

    @Override
    public CarType findById(int carTypeId) throws DAOException {
        CarType carType = null;
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
            }
        } catch (SQLException | ConnectionPoolException e) {
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
        } catch (SQLException | ConnectionPoolException e) {
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
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SAVE_NEW_CAR_TYPE_QUERY,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, carType.getTypeName());
            statement.setInt(2, carType.getRequiredLicense().getId());
            rowsAffected = statement.executeUpdate();
            carType.setCarTypeId(getGeneratedKey(statement));
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement);
        }
        return rowsAffected;
    }

    @Override
    public int deleteById(int carTypeId) throws DAOException {
        return deleteById(carTypeId, DELETE_CAR_TYPE_BY_ID_QUERY);
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
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement);
        }
        return rowsAffected;
    }
}
