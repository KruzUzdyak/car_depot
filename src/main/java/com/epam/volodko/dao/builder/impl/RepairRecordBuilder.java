package com.epam.volodko.dao.builder.impl;

import com.epam.volodko.dao.builder.Builder;
import com.epam.volodko.entity.car.RepairRecord;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RepairRecordBuilder implements Builder<RepairRecord> {

    @Override
    public RepairRecord build(ResultSet resultSet) throws SQLException {
        return null;
    }
}
