package com.epam.volodko.controller.impl.cars_page;

import com.epam.volodko.controller.Command;
import com.epam.volodko.controller.constant.Message;
import com.epam.volodko.controller.constant.PagePath;
import com.epam.volodko.controller.constant.ParameterName;
import com.epam.volodko.entity.car.CarModel;
import com.epam.volodko.entity.user.Driver;
import com.epam.volodko.entity.user.Role;
import com.epam.volodko.entity.user.User;
import com.epam.volodko.service.CarModelService;
import com.epam.volodko.service.ServiceFactory;
import com.epam.volodko.service.UserService;
import com.epam.volodko.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToAddNewCarCommand implements Command {

    private final Logger log = LogManager.getLogger(GoToAddNewCarCommand.class);
    private final CarModelService modelService = ServiceFactory.getInstance().getModelService();
    private final UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute(ParameterName.NEW_CAR, true);
        setCarModelList(request);
        setDriverList(request);

        forward(request, response, PagePath.CAR_INFO_PAGE);
    }

    private void setDriverList(HttpServletRequest request) {
        try{
            List<User> drivers = userService.getUserList(Role.DRIVER);
            request.setAttribute(ParameterName.DRIVER_LIST, drivers);
        } catch (ServiceException e){
            log.error("Catching: ", e);
            request.setAttribute(ParameterName.ERROR_MESSAGE, Message.DRIVER_LIST_LOAD_FAIL);
        }
    }

    private void setCarModelList(HttpServletRequest request){
        try{
            List<CarModel> carModels = modelService.getCarModelList();
            request.setAttribute(ParameterName.CAR_MODEL_LIST,carModels);
        } catch (ServiceException e) {
            log.error("Catching: ", e);
            request.setAttribute(ParameterName.ERROR_MESSAGE, Message.CAR_MODELS_LOAD_FAILED);
        }
    }
}
