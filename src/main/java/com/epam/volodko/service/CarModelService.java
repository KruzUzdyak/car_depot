package com.epam.volodko.service;

import com.epam.volodko.entity.car.CarModel;
import com.epam.volodko.service.exception.ServiceException;

import java.util.List;

public interface CarModelService {

    List<CarModel> getCarModelList() throws ServiceException;

    CarModel getById(int id) throws ServiceException;
}
