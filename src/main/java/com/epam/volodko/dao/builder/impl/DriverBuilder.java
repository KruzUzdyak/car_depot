package com.epam.volodko.dao.builder.impl;

import com.epam.volodko.dao.builder.BuilderFactory;
import com.epam.volodko.entity.user.Driver;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverBuilder extends UserBuilder<Driver> {

    @Override
    public Driver build(ResultSet resultSet) throws SQLException {
        Driver driver = new Driver();
        buildUser(driver, resultSet);
        DriverLicenseBuilder LicenseBuilder = BuilderFactory.getDriverLicenseBuilder();
        while(resultSet.next()){
            driver.addLicense(LicenseBuilder.build(resultSet));
        }
        return driver;
    }
}
