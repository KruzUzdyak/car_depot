package com.epam.volodko.dao.builder.impl;

import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.entity.user.DriverLicenseType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LicenseTypeBuilder {

    public DriverLicenseType build(ResultSet resultSet) throws SQLException {
        return DriverLicenseType.valueOf(resultSet.getString(Column.LICENSE_TYPE.toUpperCase()));
    }
}
