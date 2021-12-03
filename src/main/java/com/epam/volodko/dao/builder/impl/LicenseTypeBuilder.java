package com.epam.volodko.dao.builder.impl;

import com.epam.volodko.dao.builder.Builder;
import com.epam.volodko.entity.user.DriverLicenseType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LicenseTypeBuilder implements Builder<DriverLicenseType> {

    @Override
    public DriverLicenseType build(ResultSet resultSet) throws SQLException {
        return null;
    }
}
