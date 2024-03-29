package com.epam.volodko.service.impl;

import com.epam.volodko.dao.CarModelDAO;
import com.epam.volodko.dao.DAOFactory;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.car.CarModel;
import com.epam.volodko.service.CarModelService;
import com.epam.volodko.service.exception.ServiceException;

import java.util.List;

public class CarModelServiceImpl implements CarModelService {

    private static final String ID_NULL_OR_NEGATIVE = "Car model id can't be null or negative";

    private final CarModelDAO modelDAO = DAOFactory.getInstance().getCarModelDAO();

    @Override
    public CarModel getById(int id) throws ServiceException {
        if (id <= 0){
            throw new ServiceException(ID_NULL_OR_NEGATIVE);
        }
        CarModel carModel;
        try{
            carModel = modelDAO.findById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return carModel;
    }

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
