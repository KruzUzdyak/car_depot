package com.epam.volodko.dao;

public class Query {

    public static final String SQL_SCRIPTS_PATH = "src/test/resources/database/sql_scripts/";

    public static final String DROP_DATABASE = "DROP DATABASE IF EXISTS car_depot_test";
    public static final String CREATE_DATABASE = "CREATE DATABASE IF NOT EXISTS car_depot_test";
    public static final String USE_DATABASE = "USE car_depot_test";

    public static final String SQL_CREATE_LICENSE_TYPES = "create_license_types.sql";

    public static final String SQL_FILL_LICENSE_TYPES = "fill_license_types.sql";
}
