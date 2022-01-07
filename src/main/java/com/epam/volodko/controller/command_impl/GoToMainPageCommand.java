package com.epam.volodko.controller.command_impl;

import com.epam.volodko.controller.Command;
import com.epam.volodko.controller.constant.Message;
import com.epam.volodko.controller.constant.PagePath;
import com.epam.volodko.controller.constant.ParameterName;
import com.epam.volodko.service.CarService;
import com.epam.volodko.service.ServiceFactory;
import com.epam.volodko.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToMainPageCommand implements Command {

    private final Logger log = LogManager.getLogger(GoToMainPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        checkUserLoggedIn(request, response);
        saveRequest(request);

        try {
            setCarList(request);
        } catch (ServiceException e) {
            log.error("Catching: ", e);
            request.setAttribute(ParameterName.ERROR_MESSAGE, Message.CARS_LOAD_FAILED);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.MAIN_PAGE);
        dispatcher.forward(request, response);
    }

    private void setCarList(HttpServletRequest request) throws ServiceException{
        CarService service = ServiceFactory.getInstance().getCarService();
        request.setAttribute(ParameterName.CAR_LIST, service.getAllCars());
    }
}
