package com.epam.volodko.dao.builder;

import com.epam.volodko.dao.builder.impl.*;

public class BuilderFactory {

    private static final UserBuilder userBuilder = new UserBuilder();
    private static final AdminBuilder adminBuilder = new AdminBuilder();
    private static final ClientBuilder clientBuilder = new ClientBuilder();
    private static final DriverBuilder driverBuilder = new DriverBuilder();
    private static final RoleBuilder roleBuilder = new RoleBuilder();
    private static final LicenseTypeBuilder licenseTypeBuilder = new LicenseTypeBuilder();
    private static final DriverLicenseBuilder driverLicenseBuilder = new DriverLicenseBuilder();
    private static final CarBuilder carBuilder = new CarBuilder();
    private static final CarModelBuilder modelBuilder = new CarModelBuilder();
    private static final CarTypeBuilder carTypeBuilder = new CarTypeBuilder();
    private static final RefuelRecordBuilder refuelRecordBuilder = new RefuelRecordBuilder();
    private static final RepairRecordBuilder repairRecordBuilder = new RepairRecordBuilder();

    public static UserBuilder getUserBuilder() {
        return userBuilder;
    }

    public static RoleBuilder getRoleBuilder() {
        return roleBuilder;
    }

    public static AdminBuilder getAdminBuilder() {
        return adminBuilder;
    }

    public static ClientBuilder getClientBuilder() {
        return clientBuilder;
    }

    public static DriverBuilder getDriverBuilder() {
        return driverBuilder;
    }

    public static LicenseTypeBuilder getLicenseTypeBuilder() {
        return licenseTypeBuilder;
    }

    public static DriverLicenseBuilder getDriverLicenseBuilder() {
        return driverLicenseBuilder;
    }

    public static CarBuilder getCarBuilder() {
        return carBuilder;
    }

    public static CarModelBuilder getModelBuilder() {
        return modelBuilder;
    }

    public static CarTypeBuilder getCarTypeBuilder() {
        return carTypeBuilder;
    }

    public static RefuelRecordBuilder getRefuelRecordBuilder() {
        return refuelRecordBuilder;
    }

    public static RepairRecordBuilder getRepairRecordBuilder() {
        return repairRecordBuilder;
    }
}
