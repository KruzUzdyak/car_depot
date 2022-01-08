package com.epam.volodko.controller.command_impl;

import com.epam.volodko.controller.Command;
import com.epam.volodko.controller.constant.Message;
import com.epam.volodko.controller.constant.PagePath;
import com.epam.volodko.controller.constant.ParameterName;
import com.epam.volodko.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToUpdatePassCommand implements Command {

    private final Logger log = LogManager.getLogger(GoToUpdatePassCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkUserLoggedIn(request, response);
        saveRequest(request);

        try{
            setUserInfo(request);
        } catch (ServiceException e) {
            log.error("Catching: ", e);
            request.setAttribute(ParameterName.ERROR_MESSAGE, Message.USER_INFO_LOAD_FAILED);
        }
        request.setAttribute(ParameterName.USER_UPDATE_PASS, true);

        RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.USER_CABINET_PAGE);
        dispatcher.forward(request, response);
    }
}
