package com.epam.volodko.dao;

import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import org.junit.Before;

import java.sql.SQLException;

public class DriverLicenseDaoIT {

    private final DriverLicenseDAO licenseDAO = DAOFactory.getInstance().getLicenseDAO();

}
