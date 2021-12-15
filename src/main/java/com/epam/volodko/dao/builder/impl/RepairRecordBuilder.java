package com.epam.volodko.dao.builder.impl;

import com.epam.volodko.dao.DAOFactory;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.entity.car.Car;
import com.epam.volodko.entity.car.RepairRecord;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RepairRecordBuilder {

    public RepairRecord build(ResultSet resultSet) throws SQLException, DAOException {
        RepairRecord record = new RepairRecord();
        record.setId(resultSet.getInt(Column.REPAIR_RECORDS_ID));
        Date repairStart = new Date(resultSet.getLong(Column.REPAIR_RECORDS_REPAIR_START));
        Date repairEnd = new Date(resultSet.getLong(Column.REPAIR_RECORDS_REPAIR_END));
        record.setRepairStart(repairStart);
        record.setRepairEnd(repairEnd);
        record.setExpenses(resultSet.getInt(Column.REPAIR_RECORDS_EXPENSES));
        Car car = DAOFactory.getInstance().getCarDAO().findById(resultSet.getInt(Column.REPAIR_RECORDS_CAR_ID));
        record.setCar(car);
        return record;
    }
}
