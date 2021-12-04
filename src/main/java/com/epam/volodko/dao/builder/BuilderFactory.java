package com.epam.volodko.dao.builder;

import com.epam.volodko.dao.builder.impl.*;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.user.Admin;
import com.epam.volodko.entity.user.Client;
import com.epam.volodko.entity.user.Driver;
import com.epam.volodko.entity.user.Role;

public class BuilderFactory {

    private static final CarBuilder carBuilder = new CarBuilder();
    private static final CarModelBuilder carModelBuilder = new CarModelBuilder();
    private static final CarTypeBuilder carTypeBuilder = new CarTypeBuilder();
    private static final DriverLicenseBuilder driverLicenseBuilder = new DriverLicenseBuilder();
    private static final LicenseTypeBuilder licenseTypeBuilder = new LicenseTypeBuilder();
    private static final OrderBuilder orderBuilder = new OrderBuilder();
    private static final RefuelRecordBuilder refuelRecordBuilder = new RefuelRecordBuilder();
    private static final RepairRecordBuilder repairRecordBuilder = new RepairRecordBuilder();
    private static final UserBuilder<Admin> adminBuilder = new AdminBuilder();
    private static final UserBuilder<Client> clientBuilder = new ClientBuilder();
    private static final UserBuilder<Driver> driverBuilder = new DriverBuilder();

    public static UserBuilder getUserBuilder(Role role) throws DAOException {
        switch (role) {
            case ADMIN -> {
                return adminBuilder;
            }
            case CLIENT -> {
                return clientBuilder;
            }
            case DRIVER -> {
                return driverBuilder;
            }
        }
        throw new DAOException("Unable to get correct UserBuilder.");
    }

    public static CarBuilder getCarBuilder() {
        return carBuilder;
    }

    public static CarModelBuilder getCarModelBuilder() {
        return carModelBuilder;
    }

    public static CarTypeBuilder getCarTypeBuilder() {
        return carTypeBuilder;
    }

    public static DriverLicenseBuilder getDriverLicenseBuilder() {
        return driverLicenseBuilder;
    }

    public static LicenseTypeBuilder getLicenseTypeBuilder() {
        return licenseTypeBuilder;
    }

    public static OrderBuilder getOrderBuilder() {
        return orderBuilder;
    }

    public static RefuelRecordBuilder getRefuelRecordBuilder() {
        return refuelRecordBuilder;
    }

    public static RepairRecordBuilder getRepairRecordBuilder() {
        return repairRecordBuilder;
    }


}
