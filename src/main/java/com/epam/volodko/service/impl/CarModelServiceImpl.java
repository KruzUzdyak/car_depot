package com.epam.volodko.service.impl;

import com.epam.volodko.dao.CarModelDAO;
import com.epam.volodko.dao.DAOFactory;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.car.CarModel;
import com.epam.volodko.service.CarModelService;
import com.epam.volodko.service.exception.ServiceException;

import java.util.List;

public class CarModelServiceImpl implements CarModelService {

    private final CarModelDAO modelDAO = DAOFactory.getInstance().getCarModelDAO();

    @Override
    public List<CarModel> getCarModelList() throws ServiceException {
        List<CarModel> carModels;
        try{
            carModels = modelDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return carModels;
    }
}
