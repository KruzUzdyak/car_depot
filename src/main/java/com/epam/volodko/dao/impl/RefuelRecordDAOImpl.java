package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.RefuelRecordDAO;
import com.epam.volodko.dao.builder.BuilderFactory;
import com.epam.volodko.dao.database.ConnectionPool;
import com.epam.volodko.dao.database.pool_exception.ConnectionPoolException;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.dao.table_name.Table;
import com.epam.volodko.entity.car.RefuelRecord;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RefuelRecordDAOImpl extends AbstractDAO implements RefuelRecordDAO {

    private static final String FIND_REFUEL_RECORD_BY_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s = ?;",
            Table.REFUEL_RECORDS, Column.REFUEL_RECORDS_ID);
    private static final String FIND_ALL_REFUEL_RECORD_QUERY = String.format(
            "SELECT * FROM %s;",
            Table.REFUEL_RECORDS);
    private static final String SAVE_NEW_REFUEL_RECORD_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s, %s) VALUES (?, ?, ?, ?);",
            Table.REFUEL_RECORDS, Column.REFUEL_RECORDS_REFUEL_DATE, Column.REFUEL_RECORDS_FUEL_PRICE,
            Column.REFUEL_RECORDS_REFUEL_AMOUNT, Column.REFUEL_RECORDS_CAR_ID);
    private static final String DELETE_REFUEL_RECORD_QUERY = String.format(
            "DELETE FROM %s WHERE %s = ?;",
            Table.REFUEL_RECORDS, Column.REFUEL_RECORDS_ID);
    private static final String UPDATE_REFUEL_RECORD_QUERY = String.format(
            "UPDATE %s SET %s=?, %s=?, %s=?, %s=? WHERE %s=?;",
            Table.REFUEL_RECORDS, Column.REFUEL_RECORDS_REFUEL_DATE, Column.REFUEL_RECORDS_FUEL_PRICE,
            Column.REFUEL_RECORDS_REFUEL_AMOUNT, Column.REFUEL_RECORDS_CAR_ID, Column.REFUEL_RECORDS_ID);

    @Override
    public RefuelRecord findById(int id) throws DAOException {
        RefuelRecord record = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(FIND_REFUEL_RECORD_BY_ID_QUERY);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                record = BuilderFactory.getRefuelRecordBuilder().build(resultSet);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return record;
    }

    @Override
    public List<RefuelRecord> findAll() throws DAOException {
        List<RefuelRecord> records = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL_REFUEL_RECORD_QUERY);
            while (resultSet.next()){
                RefuelRecord record = BuilderFactory.getRefuelRecordBuilder().build(resultSet);
                records.add(record);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return records;
    }

    @Override
    public int saveNew(RefuelRecord record) throws DAOException {
        int rowsAffected;
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SAVE_NEW_REFUEL_RECORD_QUERY);
            prepareForSaveRefuelRecordStatement(record, statement);
            rowsAffected = statement.executeUpdate();
            record.setId(getGeneratedKey(statement));
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement);
        }
        return rowsAffected;
    }

    @Override
    public int deleteById(int id) throws DAOException {
        return deleteById(id, DELETE_REFUEL_RECORD_QUERY);
    }

    @Override
    public int update(RefuelRecord record) throws DAOException {
        int rowsAffected;
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(UPDATE_REFUEL_RECORD_QUERY);
            prepareForSaveRefuelRecordStatement(record, statement);
            statement.setInt(5, record.getId());
            rowsAffected = statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement);
        }
        return rowsAffected;
    }

    private void prepareForSaveRefuelRecordStatement(RefuelRecord record, PreparedStatement statement)
            throws SQLException {
        statement.setLong(1, record.getRefuelDate().getTime());
        statement.setInt(2, record.getFuelPrice());
        statement.setInt(3, record.getRefuelAmount());
        statement.setInt(4, record.getCar().getId());
    }
}
