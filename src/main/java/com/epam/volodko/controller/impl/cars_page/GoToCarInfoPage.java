package com.epam.volodko.controller.impl.cars_page;

import com.epam.volodko.controller.Command;
import com.epam.volodko.controller.constant.Message;
import com.epam.volodko.controller.constant.PagePath;
import com.epam.volodko.controller.constant.ParameterName;
import com.epam.volodko.entity.car.Car;
import com.epam.volodko.service.CarService;
import com.epam.volodko.service.ServiceFactory;
import com.epam.volodko.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToCarInfoPage implements Command {

    private final Logger log = LogManager.getLogger(GoToCarInfoPage.class);
    private final CarService carService = ServiceFactory.getInstance().getCarService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            setCarInfo(request);
        } catch (ServiceException e){
            log.error("Catching: ", e);
            request.setAttribute(ParameterName.ERROR_MESSAGE, Message.CAR_INFO_LOAD_FAIL);
        }

        forward(request, response, PagePath.CAR_INFO_PAGE);
    }

    private void setCarInfo(HttpServletRequest request) throws ServiceException{
        String carRequestType = request.getParameter(ParameterName.CAR_REQUEST_TYPE);
        Car car = null;
        switch (carRequestType){
            case ParameterName.CAR_BY_DRIVER_ID -> {
                int driverId = (int) request.getSession().getAttribute(ParameterName.USER_ID);
                car = carService.getCarByDriverId(driverId);
            }
            default -> request.setAttribute(ParameterName.ERROR_MESSAGE, Message.CAR_INFO_LOAD_FAIL);
        }
        request.setAttribute(ParameterName.CAR, car);
    }
}
