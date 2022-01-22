package com.epam.volodko.dao.builder.impl;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.entity.car.CarType;
import com.epam.volodko.entity.user.DriverLicenseType;
import com.epam.volodko.entity.user.LicenseTypeProvider;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarTypeBuilder {

    public CarType build(ResultSet resultSet) throws DAOException {
        CarType carType;
        try {
            int carTypeId = resultSet.getInt(Column.CAR_TYPES_ID);
            String typeName = resultSet.getString(Column.CAR_TYPES_NAME);
            DriverLicenseType license = LicenseTypeProvider.getLicenseType(
                    resultSet.getInt(Column.CAR_TYPES_REQUIRED_LICENSE_ID));
            carType = new CarType(carTypeId, typeName, license);
        } catch (SQLException e) {
            throw new DAOException("SQLException when try to build car type.", e);
        }
        return carType;
    }
}
