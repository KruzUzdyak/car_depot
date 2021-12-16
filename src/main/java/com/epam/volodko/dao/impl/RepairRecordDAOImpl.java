package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.RepairRecordDAO;
import com.epam.volodko.dao.builder.BuilderFactory;
import com.epam.volodko.dao.database.ConnectionPool;
import com.epam.volodko.dao.database.pool_exception.ConnectionPoolException;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.dao.table_name.Table;
import com.epam.volodko.entity.car.RepairRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepairRecordDAOImpl extends AbstractDAO implements RepairRecordDAO {

    private static final String FIND_REPAIR_RECORD_BY_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s = ?;",
            Table.REPAIR_RECORDS, Column.REPAIR_RECORDS_ID);
    private static final String FIND_ALL_REPAIR_RECORD_QUERY = String.format(
            "SELECT * FROM %s;",
            Table.REPAIR_RECORDS);

    @Override
    public RepairRecord findById(int id) throws DAOException {
        RepairRecord record = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(FIND_REPAIR_RECORD_BY_ID_QUERY);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                record = BuilderFactory.getRepairRecordBuilder().build(resultSet);
            }
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return record;
    }

    @Override
    public List<RepairRecord> findAll() throws DAOException {
        List<RepairRecord> records = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(FIND_ALL_REPAIR_RECORD_QUERY);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                RepairRecord record = BuilderFactory.getRepairRecordBuilder().build(resultSet);
                records.add(record);
            }
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return records;
    }

    @Override
    public int saveNew(RepairRecord record) throws DAOException {
        return 0;
    }

    @Override
    public int deleteById(int id) throws DAOException {
        return 0;
    }

    @Override
    public int update(RepairRecord record) throws DAOException {
        return 0;
    }
}
