package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.car.Car;
import com.epam.volodko.entity.user.Driver;
import com.epam.volodko.entity.user.Role;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CarDAOTest {

    CarDAO carDAO = DAOFactory.getInstance().getCarDAO();
    UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

    @Test
    public void checkFindCarById() throws DAOException {
        Car actualCar = carDAO.findCarById(4);

        System.out.println(actualCar);

        //todo make full test.
    }

    @Test
    public void checkFindCarByDriver() throws DAOException {
        Driver driver = (Driver) userDAO.findUsersByRole(Role.DRIVER).get(0);
        Car actualCar = carDAO.findCarByDriver(driver);

        System.out.println(actualCar);
        //todo make full test.
    }

    @Test
    public void checkFindAllCars() throws DAOException{
        List<Car> cars = carDAO.findAllCars();
        for (Car car : cars){
            System.out.println(car);
            System.out.println();
        }
    }

}