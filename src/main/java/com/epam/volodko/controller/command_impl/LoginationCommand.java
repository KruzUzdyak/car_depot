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

public class LoginationCommand extends RequestSaver implements Command {

    private static final Logger log = LogManager.getLogger(LoginationCommand.class);

    private static final String LOGINATION_MESSAGE_TEXT = "Now you are logged in.";
    private static final String LOGINATION_FAILED_MESSAGE_TEXT = "Wrong login or password.";
    private static final String LOGINATION_EXCEPTION_MESSAGE_TEXT = "Login can't be done. We working on this issue.";
    private static final String LOGINATION_REDIRECT_COMMAND = String.format("%s?%s=%s&%s=%s",
            CommandName.CONTROLLER, CommandName.COMMAND, CommandName.GO_TO_MAIN_PAGE,
            ParameterName.LOGINATION_MESSAGE, LOGINATION_MESSAGE_TEXT);

    private final UserService userService = ServiceFactory.getInstance().getUserService();
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        saveRequest(request);
        String login = request.getParameter(ParameterName.USER_LOGIN);
        // TODO: 12.12.2021 password hashing
        String password = request.getParameter(ParameterName.USER_PASSWORD);
        User user;
        try {
            user = userService.processLogination(login, password);
            if (user != null){
                HttpSession session = request.getSession();
                session.setAttribute(ParameterName.USER_LOGIN, user.getId());
                session.setAttribute(ParameterName.USER_ROLE, user.getRole());
                response.sendRedirect(LOGINATION_REDIRECT_COMMAND);
            } else {
                log.warn(String.format("Logination failed with user login - %s.", login));
                forwardOnFailedLogination(request, response, LOGINATION_FAILED_MESSAGE_TEXT);
            }
        } catch (ServiceException e) {
            log.error("Catching:", e);
            forwardOnFailedLogination(request, response, LOGINATION_EXCEPTION_MESSAGE_TEXT);
        }
    }

    private void forwardOnFailedLogination(HttpServletRequest request, HttpServletResponse response, String message)
            throws ServletException, IOException {
        request.setAttribute(ParameterName.ERROR_MESSAGE, message);
        RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.LOGINATION_PAGE);
        dispatcher.forward(request, response);
    }


}
