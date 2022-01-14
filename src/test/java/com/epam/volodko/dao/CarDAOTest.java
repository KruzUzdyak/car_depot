package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.car.Car;
import com.epam.volodko.entity.car.CarModel;
import com.epam.volodko.entity.user.Driver;
import com.epam.volodko.entity.user.Role;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CarDAOTest {

    CarDAO carDAO = DAOFactory.getInstance().getCarDAO();
    UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

    @Test
    public void testFindCarById() throws DAOException {
        Car actualCar = carDAO.findById(1);

        System.out.println(actualCar);

        //todo make full test.
    }

    @Test
    public void testFindCarByDriver() throws DAOException {
        int driverId = 2;
        Car actualCar = carDAO.findByDriver(driverId);

        System.out.println(actualCar);
        //todo make full test.
    }

    @Test
    public void testFindAllCars() throws DAOException{
        List<Car> cars = carDAO.findAll();
        for (Car car : cars){
            System.out.println(car);
            System.out.println();
        }
    }

    @Test
    public void testSaveNew() throws DAOException{
        CarModel model = new CarModel();
        model.setId(1);
        Driver driver = new Driver();
        driver.setId(2);
        Car car = new Car(0, "TEST_PLATE_NUMBER", 40, 555,
                false, model, driver);

        int rowsAffected = carDAO.saveNew(car);
        int expectedAffect = 1;

        assertEquals(expectedAffect, rowsAffected);
        assertTrue(car.getId() > 0);
    }

    @Test
    public void testDelete() throws DAOException{
        int carId = 1;

        int rowsAffected = carDAO.deleteById(carId);
        int expectedAffect = 1;
        Car actualCar = carDAO.findById(carId);

        assertEquals(expectedAffect, rowsAffected);
        assertNull(actualCar);
    }

}