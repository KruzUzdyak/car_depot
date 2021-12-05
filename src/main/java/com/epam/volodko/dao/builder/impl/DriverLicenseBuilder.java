package com.epam.volodko.dao.builder.impl;

import com.epam.volodko.dao.builder.BuilderFactory;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.entity.user.DriverLicense;
import com.epam.volodko.entity.user.DriverLicenseType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class DriverLicenseBuilder {

    public DriverLicense build(ResultSet resultSet) throws SQLException {
        DriverLicenseType type = BuilderFactory.getLicenseTypeBuilder().build(resultSet);
        Date obtainingDate = resultSet.getDate(Column.DRIVER_LICENSES_OBTAINING_DATE);
        String licenseNumber = resultSet.getString(Column.DRIVER_LICENSES_LICENSE_NUMBER);
        return new DriverLicense(type, obtainingDate, licenseNumber);
    }
}
