package com.epam.volodko.dao.builder.impl;

import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.entity.user.DriverLicenseType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LicenseTypeBuilder {

    public DriverLicenseType build(ResultSet resultSet) throws SQLException {
        String licenseType = resultSet.getString(Column.LICENSE_TYPE);
        if (licenseType != null){
            return DriverLicenseType.valueOf(licenseType.toUpperCase());
        }
        return null;
    }
}
