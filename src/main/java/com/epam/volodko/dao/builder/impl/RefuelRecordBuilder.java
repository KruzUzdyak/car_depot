package com.epam.volodko.dao.builder.impl;

import com.epam.volodko.dao.DAOFactory;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.entity.car.Car;
import com.epam.volodko.entity.car.RefuelRecord;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefuelRecordBuilder {

    public RefuelRecord build(ResultSet resultSet) throws SQLException, DAOException {
        RefuelRecord record = new RefuelRecord();
        record.setId(resultSet.getInt(Column.REFUEL_RECORDS_ID));
        Date refuelDate = new Date(resultSet.getLong(Column.REFUEL_RECORDS_REFUEL_DATE));
        record.setRefuelDate(refuelDate);
        record.setFuelPrice(resultSet.getInt(Column.REFUEL_RECORDS_FUEL_PRICE));
        record.setRefuelAmount(resultSet.getInt(Column.REFUEL_RECORDS_REFUEL_AMOUNT));
        Car car = DAOFactory.getInstance().getCarDAO().findById(resultSet.getInt(Column.REFUEL_RECORDS_CAR_ID));
        record.setCar(car);
        return record;
    }
}
