package com.epam.volodko.dao.builder.impl;

import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.entity.user.DriverLicenseType;
import com.epam.volodko.entity.user.LicenseTypeProvider;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LicenseTypeBuilder {

    public DriverLicenseType build(ResultSet resultSet) throws SQLException {
        return LicenseTypeProvider.getLicenseType(resultSet.getInt(Column.LICENSE_ID));
    }
}
