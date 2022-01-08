package com.epam.volodko.dao.builder.impl;

import com.epam.volodko.dao.builder.BuilderFactory;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.entity.user.DriverLicense;
import com.epam.volodko.entity.user.DriverLicenseType;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverLicenseBuilder {

    public DriverLicense build(ResultSet resultSet) throws SQLException {
        DriverLicense license = new DriverLicense();
        license.setLicenseType(BuilderFactory.getLicenseTypeBuilder().build(resultSet));
        if (license.getLicenseType() != null){
            license.setObtainingDate(new Date(resultSet.getLong(Column.DRIVER_LICENSES_OBTAINING_DATE)));
        } else{
            license.setObtainingDate(null);
        }
        license.setLicenseNumber(resultSet.getString(Column.DRIVER_LICENSES_LICENSE_NUMBER));
        return license;
    }
}
