package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.car.CarModel;
import com.epam.volodko.entity.car.CarType;
import org.junit.Test;

import java.util.List;

public class CarModelDAOTest {

    CarTypeDAO typeDAO = DAOFactory.getInstance().getCarTypeDAO();
    CarModelDAO carModelDAO = DAOFactory.getInstance().getCarModelDAO();

    @Test
    public void testFindById() throws DAOException {
        int carModelId = 1;
        CarModel model = carModelDAO.findById(carModelId);
        System.out.println(model);

        //todo make full test.
    }

    @Test
    public void testFindByCarType() throws DAOException {
        CarType type = new CarType(3, null, null);
        List<CarModel> models = carModelDAO.findById(type);
        for (CarModel model : models) {
            System.out.println(model);
            System.out.println();
        }
        //todo make full test.
    }

    @Test
    public void testFindALl() throws DAOException {
        List<CarModel> models = carModelDAO.findAll();
        for (CarModel model : models) {
            System.out.println(model);
            System.out.println();
        }
        //todo make full test.
    }

    @Test
    public void testSaveNewUpdateAndDelete() throws DAOException {
        CarType type = typeDAO.findAll().get(0);
        int rowsAffected;
        CarModel model = new CarModel(100, "SaveTestModelNAme", 10, 20, "nice things", type);
        rowsAffected = carModelDAO.saveNew(model);
        System.out.println("Rows affected by saving new car model - " + rowsAffected);
        System.out.println(model);

        model.setModelName("NewUpdatedName");
        model.setCapacity(1050);
        rowsAffected = carModelDAO.update(model);
        model = carModelDAO.findById(model.getCarModelId());
        System.out.println("Rows affected by updating car model - " + rowsAffected);
        System.out.println(model);

        rowsAffected = carModelDAO.deleteById(model.getCarModelId());
        System.out.println("Rows affected by deleting test car model - " + rowsAffected);

    }

}