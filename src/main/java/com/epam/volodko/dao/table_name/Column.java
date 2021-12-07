package com.epam.volodko.dao.table_name;

public class Column {

    public static final String USERS_ID = "user_id";
    public static final String USERS_LOGIN = "login";
    public static final String USERS_PASSWORD = "password";
    public static final String USERS_NAME = "name";
    public static final String USERS_PHONE = "phone";
    public static final String USERS_ROLE_ID = "role_id";

    public static final String CLIENT_INFO_USER_ID = "user_id";
    public static final String CLIENT_INFO_COMPANY = "company";
    public static final String CLIENT_INFO_NOTE = "note";

    public static final String ADMIN_INFO_USER_ID = "user_id";
    public static final String ADMIN_INDO_WORKS_SINCE = "works_since";
    public static final String ADMIN_INFO_NOTE = "note";

    public static final String ROLES_ID = "role_id";
    public static final String ROLES_ROLE = "role";

    public static final String LICENSE_ID = "license_id";
    public static final String LICENSE_TYPE = "license_type";

    public static final String DRIVER_LICENSES_USER_ID = "user_id";
    public static final String DRIVER_LICENSES_LICENSE_ID = "license_id";
    public static final String DRIVER_LICENSES_OBTAINING_DATE = "obtaining_date";
    public static final String DRIVER_LICENSES_LICENSE_NUMBER = "license_number";

    public static final String ORDERS_ORDER_ID = "order_id";
    public static final String ORDERS_DEST_FROM = "dest_from";
    public static final String ORDERS_DEST_TO = "dest_to";
    public static final String ORDERS_DISTANCE = "distance";
    public static final String ORDERS_DATE_START = "date_start";
    public static final String ORDERS_DATE_END = "date_end";
    public static final String ORDERS_LOAD = "load";
    public static final String ORDERS_LOAD_NOTE = "load_note";
    public static final String ORDERS_COMPLETED = "completed";
    public static final String ORDERS_PAYMENT = "payment";
    public static final String ORDERS_CLIENT_ID = "client_id";
    public static final String ORDERS_ADMIN_ID = "admin_id";
    public static final String ORDERS_CAR_ID = "car_id";

    public static final String CARS_ID = "car_id";
    public static final String CARS_PLATE_NUMBER = "plate_number";
    public static final String CARS_FUEL_LEVEL = "fuel_level";
    public static final String CARS_MILEAGE = "mileage";
    public static final String CARS_BROKEN = "broken";
    public static final String CARS_MODEL_ID = "car_model_id";
    public static final String CARS_DRIVER_ID = "driver_id";

    public static final String CAR_MODELS_ID = "model_id";
    public static final String CAR_MODELS_NAME = "car_model";
    public static final String CAR_MODELS_CAPACITY = "capacity";
    public static final String CAR_MODELS_LOAD_TYPE = "load_type";
    public static final String CAR_MODELS_FUEL_TANK = "fuel_tank";
    public static final String CAR_MODELS_TYPE_ID = "car_type_id";

    public static final String CAR_TYPES_ID = "type_id";
    public static final String CAR_TYPES_NAME = "car_type";
    public static final String CAR_TYPES_REQUIRED_LICENSE_ID = "required_license_type";

    public static final String REFUEL_RECORDS_ID = "refuel_record_id";
    public static final String REFUEL_RECORDS_REFUEL_DATE = "refuel_date";
    public static final String REFUEL_RECORDS_FUEL_PRICE = "fuel_price";
    public static final String REFUEL_RECORDS_REFUEL_AMOUNT = "refuel_amount";
    public static final String REFUEL_RECORDS_CAR_ID = "car_id";

    public static final String REPAIR_RECORDS_ID = "repair_record_id";
    public static final String REPAIR_RECORDS_REPAIR_START = "repair_start";
    public static final String REPAIR_RECORDS_REPAIR_END = "repair_end";
    public static final String REPAIR_RECORDS_EXPENSES = "expenses";
    public static final String REPAIR_RECORDS_CAR_ID = "car_id";

}
