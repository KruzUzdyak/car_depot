package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.car.CarModel;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarModelDAOTest {

    CarModelDAO carModelDAO = DAOFactory.getInstance().getCarModelDAO();

    @Test
    public void testFindById() throws DAOException{
        int carModelId = 1;
        CarModel model = carModelDAO.getById(carModelId);
        System.out.println(model);

        //todo make full test.
    }

}