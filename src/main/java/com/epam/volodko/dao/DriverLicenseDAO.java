package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.user.DriverLicense;

public interface DriverLicenseDAO {

    int saveNew(int driverId, DriverLicense license) throws DAOException;

    int deleteById(int driverId, int licenseTypeId) throws DAOException;
}
