package com.epam.volodko.controller.impl.cars_page;

import com.epam.volodko.controller.Command;
import com.epam.volodko.controller.constant.CommandName;
import com.epam.volodko.controller.constant.Message;
import com.epam.volodko.controller.constant.PagePath;
import com.epam.volodko.controller.constant.ParameterName;
import com.epam.volodko.entity.car.Car;
import com.epam.volodko.entity.car.CarModel;
import com.epam.volodko.entity.user.Driver;
import com.epam.volodko.entity.user.Role;
import com.epam.volodko.service.CarModelService;
import com.epam.volodko.service.CarService;
import com.epam.volodko.service.ServiceFactory;
import com.epam.volodko.service.UserService;
import com.epam.volodko.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddNewCarCommand implements Command {

    private static final String ADD_NEW_CAR_REDIRECT_COMMAND = String.format("%s?%s=%s&%s=%s&%s=",
            CommandName.CONTROLLER, CommandName.COMMAND, CommandName.GO_TO_CAR_INFO_PAGE,
            ParameterName.CAR_REQUEST_TYPE, ParameterName.CAR_BY_ID, ParameterName.CAR_ID);
    private static final String REDIRECT_ON_FAIL = String.format("%s?%s=%s&%s=%s",
            CommandName.CONTROLLER, CommandName.COMMAND, CommandName.GO_TO_ADD_NEW_CAR,
            ParameterName.ERROR_MESSAGE, Message.ADD_NEW_CAR_FAILED);
    private static final String CHECKBOX_PRESSED = "on";

    private final Logger log = LogManager.getLogger(AddNewCarCommand.class);
    private final CarService carService = ServiceFactory.getInstance().getCarService();
    private final CarModelService modelService = ServiceFactory.getInstance().getModelService();
    private final UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Car car = prepareCarForSave(request);
            if (carService.saveCar(car)){
                response.sendRedirect(String.format("%s%d", ADD_NEW_CAR_REDIRECT_COMMAND, car.getId()));
            }else {
                request.setAttribute(ParameterName.NEW_CAR, true);
                response.sendRedirect(REDIRECT_ON_FAIL);
            }
        } catch (ServiceException e) {
            log.error("Catching: ", e);
            response.sendRedirect(REDIRECT_ON_FAIL);
        }
    }

    private Car prepareCarForSave(HttpServletRequest request) throws ServiceException {
        boolean isBroken = isCheckBoxPressed(request);
        int mileage = Integer.parseInt(request.getParameter(ParameterName.CAR_MILEAGE));
        int fuelLevel = Integer.parseInt(request.getParameter(ParameterName.CAR_FUEL_LEVEL));
        Car car = new Car();
        car.setPlateNumber(request.getParameter(ParameterName.CAR_PLATE_NUMBER));
        car.setBroken(isBroken);
        car.setMileage(mileage);
        car.setFuelLevel(fuelLevel);
        car.setModel(prepareCarModel(request));
        car.setDriver(prepareDriver(request));
        return car;
    }

    private boolean isCheckBoxPressed(HttpServletRequest request) {
        return CHECKBOX_PRESSED.equals(request.getParameter(ParameterName.CAR_BROKEN));
    }

    private Driver prepareDriver(HttpServletRequest request) throws ServiceException {
        int driverId = Integer.parseInt(request.getParameter(ParameterName.CAR_DRIVER_ID));
        return (Driver) userService.getUser(driverId, Role.DRIVER);
    }

    private CarModel prepareCarModel(HttpServletRequest request) throws ServiceException {
        int carModelId = Integer.parseInt(request.getParameter(ParameterName.CAR_MODEL_ID));
        return modelService.getById(carModelId);
    }
}
