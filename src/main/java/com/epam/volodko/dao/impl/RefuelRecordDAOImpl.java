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
            e.printStackTrace();
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
            e.printStackTrace();
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return records;
    }

    @Override
    public int saveNew(RefuelRecord record) throws DAOException {
        return 0;
    }

    @Override
    public int delete(RefuelRecord record) throws DAOException {
        return 0;
    }

    @Override
    public int update(RefuelRecord record) throws DAOException {
        return 0;
    }
}
