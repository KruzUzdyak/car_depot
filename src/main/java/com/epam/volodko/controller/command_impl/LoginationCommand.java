package com.epam.volodko.controller.command_impl;

import com.epam.volodko.controller.Command;
import com.epam.volodko.controller.constant.CommandName;
import com.epam.volodko.controller.constant.PagePath;
import com.epam.volodko.controller.constant.ParameterName;
import com.epam.volodko.entity.user.User;
import com.epam.volodko.service.ServiceFactory;
import com.epam.volodko.service.UserService;
import com.epam.volodko.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginationCommand implements Command {

    private static final Logger log = LogManager.getLogger(LoginationCommand.class);

    private static final String LOGINATION_MESSAGE_TEXT = "Now you are logged in.";
    private static final String LOGINATION_ERROR_MESSAGE_TEXT = "Wrong login or password.";
    private static final String LOGINATION_MESSAGE_ON_EXCEPTION_TEXT = "Login can't be done. We working on this issue.";
    private static final String REDIRECT_COMMAND = String.format("%s?%s=%s&%s=%s",
            CommandName.CONTROLLER, CommandName.COMMAND, CommandName.GO_TO_MAIN_PAGE,
            ParameterName.LOGINATION_MESSAGE, LOGINATION_MESSAGE_TEXT);

    private final UserService userService = ServiceFactory.getInstance().getUserService();
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter(ParameterName.USER_LOGIN);
        // TODO: 12.12.2021 password hashing
        String password = request.getParameter(ParameterName.USER_PASSWORD);

        try {
            if (userService.validateLogination(login, password)){
                HttpSession session = request.getSession();
                session.setAttribute("userRole", "empty");
                response.sendRedirect(REDIRECT_COMMAND);
            } else {
                log.warn(String.format("Logination failed with user login - %s.", login));
                forwardOnFailedLogination(request, response, LOGINATION_ERROR_MESSAGE_TEXT);
            }
        } catch (ServiceException e) {
            log.warn("Catching", e);
            forwardOnFailedLogination(request, response, LOGINATION_MESSAGE_ON_EXCEPTION_TEXT);
        }
    }

    private void forwardOnFailedLogination(HttpServletRequest request, HttpServletResponse response, String message)
            throws ServletException, IOException {
        request.setAttribute(ParameterName.ERROR_MESSAGE, message);
        RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.LOGINATION_PAGE);
        dispatcher.forward(request, response);
    }


}
