package com.epam.volodko.dao.builder;

public class BuilderFactory {

    private static final CarBuilder carBuilder = new CarBuilder();
    private static final CarModelBuilder carModelBuilder = new CarModelBuilder();
    private static final CarTypeBuilder carTypeBuilder = new CarTypeBuilder();
    private static final ClientInfoBuilder clientInfoBuilder = new ClientInfoBuilder();
    private static final DriverLicenseBuilder driverLicenseBuilder = new DriverLicenseBuilder();
    private static final LicenseTypeBuilder licenseTypeBuilder = new LicenseTypeBuilder();
    private static final OrderBuilder orderBuilder = new OrderBuilder();
    private static final RefuelRecordBuilder refuelRecordBuilder = new RefuelRecordBuilder();
    private static final RepairRecordBuilder repairRecordBuilder = new RepairRecordBuilder();
    private static final RoleBuilder roleBuilder = new RoleBuilder();
    private static final UserBuilder userBuilder = new UserBuilder();

    public static UserBuilder getUserBuilder() {
        return userBuilder;
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

    public static ClientInfoBuilder getClientInfoBuilder() {
        return clientInfoBuilder;
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

    public static RoleBuilder getRoleBuilder() {
        return roleBuilder;
    }
}
