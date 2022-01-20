package com.epam.volodko.dao.builder.impl;

import com.epam.volodko.dao.builder.BuilderFactory;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.entity.user.Driver;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverBuilder extends UserBuilder {

    public Driver build(ResultSet resultSet) throws SQLException {
        Driver driver = new Driver();
        buildDriver(driver, resultSet);
        return driver;
    }

    private void buildDriver(Driver driver, ResultSet resultSet) throws SQLException {
        buildUser(driver, resultSet);

        DriverLicenseBuilder licenseBuilder = BuilderFactory.getDriverLicenseBuilder();
        driver.addLicense(licenseBuilder.build(resultSet));
        int currentDriverId = driver.getId();
        while (resultSet.next()) {
            int nextDriverId = resultSet.getInt(Column.USERS_ID);
            if (currentDriverId == nextDriverId) {
                driver.addLicense(licenseBuilder.build(resultSet));
            } else {
                resultSet.previous();
                break;
            }
        }
    }
}
