package com.epam.volodko.dao.builder.impl;

import com.epam.volodko.dao.builder.Builder;
import com.epam.volodko.entity.user.DriverLicense;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverLicenseBuilder implements Builder<DriverLicense> {

    @Override
    public DriverLicense build(ResultSet resultSet) throws SQLException {
        return null;
    }
}
