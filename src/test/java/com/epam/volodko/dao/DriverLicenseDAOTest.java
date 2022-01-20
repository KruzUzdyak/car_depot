package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.user.DriverLicense;
import com.epam.volodko.entity.user.DriverLicenseType;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class DriverLicenseDAOTest {

    DriverLicenseDAO licenseDAO = DAOFactory.getInstance().getLicenseDAO();

    @Test
    public void testSaveNew() throws DAOException{
        DriverLicense license = new DriverLicense(
                DriverLicenseType.A, new Date(), "test license number");
        int driverId = 2;
        int rowsAffected = licenseDAO.saveNew(driverId, license);
        int expectedAffect = 1;

        assertEquals(expectedAffect, rowsAffected);
    }

    @Test
    public void testDeleteById() throws DAOException{
        int driverId = 2;
        DriverLicenseType licenseType = DriverLicenseType.A;
        int rowsAffected = licenseDAO.deleteById(driverId, licenseType.getId());
        int expectedAffect = 1;

        assertEquals(expectedAffect, rowsAffected);

    }



}