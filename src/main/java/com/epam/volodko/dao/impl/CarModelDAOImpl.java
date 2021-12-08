package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.CarModelDAO;
import com.epam.volodko.dao.DAOFactory;
import com.epam.volodko.dao.builder.BuilderFactory;
import com.epam.volodko.dao.database.ConnectionPool;
import com.epam.volodko.dao.database.pool_exception.ConnectionPoolException;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.dao.table_name.Table;
import com.epam.volodko.entity.car.CarModel;
import com.epam.volodko.entity.car.CarType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CarModelDAOImpl extends AbstractDAO implements CarModelDAO {

    private static final String GET_CAR_MODEL_BY_ID_QUERY = String.format(
            "SELECT * FROM %s AS cm JOIN %s AS ct ON cm.%s = ct.%s JOIN %s AS lt ON ct.%s = lt.%s WHERE %s = ?;",
            Table.CAR_MODELS, Table.CAR_TYPES, Column.CAR_MODELS_TYPE_ID, Column.CAR_TYPES_ID,
            Table.LICENSE_TYPES, Column.CAR_TYPES_REQUIRED_LICENSE_ID, Column.LICENSE_ID ,Column.CAR_MODELS_ID);

    @Override
    public CarModel getById(int carModelId) throws DAOException {
        CarModel carModel;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            connection.setAutoCommit(false);
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
    public CarModel getByCarType(CarType carType) throws DAOException {
        return null;
    }

    @Override
    public List<CarModel> getAll() throws DAOException {
        return null;
    }

    @Override
    public int saveNew(CarModel carModel) throws DAOException {
        return 0;
    }

    @Override
    public int deleteById(int carModelId) throws DAOException {
        return 0;
    }

    @Override
    public int update(CarModel carModel) throws DAOException {
        return 0;
    }
}
