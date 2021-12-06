package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.car.Car;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarDAOTest {

    CarDAO carDAO = DAOFactory.getInstance().getCarDAO();

    @Test
    public void checkFindCarById() throws DAOException {
        Car car = carDAO.findCarById(4);

        System.out.println(car);
    }

}