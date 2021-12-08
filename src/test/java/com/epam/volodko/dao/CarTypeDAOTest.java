package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.car.CarType;
import com.epam.volodko.entity.user.DriverLicenseType;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CarTypeDAOTest {

    CarTypeDAO carTypeDAO = DAOFactory.getInstance().getCarTypeDAO();

    @Test
    public void testFindById() throws DAOException{
        int carTypeId = 1;
        CarType carType = carTypeDAO.findById(carTypeId);

        System.out.println(carType);
        //todo make full test.
    }

    @Test
    public void testFindAll() throws DAOException{
        List<CarType> carTypes = carTypeDAO.findAll();
        for (CarType carType : carTypes){
            System.out.println(carType);
            System.out.println();
        }
        //todo make full test.
    }

    @Test
    public void testSaveNewUpdateAndDelete() throws DAOException{
        CarType carType = new CarType(100, "NewTestCarType", DriverLicenseType.A1);
        int rowsAffected = carTypeDAO.saveNew(carType);
        System.out.println("Rows affected by saving new car type - " + rowsAffected);
        System.out.println(carType);
        carType.setTypeName("Updated");
        carType.setRequiredLicense(DriverLicenseType.I);
        rowsAffected = carTypeDAO.update(carType);
        System.out.println("Rows affecte by updating car type - " + rowsAffected);
        rowsAffected = carTypeDAO.deleteById(carType.getCarTypeId());
        System.out.println("Rows affected by deleting car type - " + rowsAffected);

        //todo make full test.
    }


}