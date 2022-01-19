package com.epam.volodko.service;

import com.epam.volodko.service.impl.CarModelServiceImpl;
import com.epam.volodko.service.impl.CarServiceImpl;
import com.epam.volodko.service.impl.OrderServiceImpl;
import com.epam.volodko.service.impl.UserServiceImpl;

public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private final UserService userService = new UserServiceImpl();
    private final CarService carService = new CarServiceImpl();
    private final CarModelService modelService = new CarModelServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public CarService getCarService() {
        return carService;
    }

    public CarModelService getModelService() {
        return modelService;
    }

    public OrderService getOrderService() {
        return orderService;
    }
}
