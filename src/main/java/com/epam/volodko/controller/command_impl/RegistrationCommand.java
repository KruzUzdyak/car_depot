package com.epam.volodko.controller.command_impl;

import com.epam.volodko.controller.Command;
import com.epam.volodko.controller.constant.CommandName;
import com.epam.volodko.controller.constant.PagePath;
import com.epam.volodko.controller.constant.ParameterName;
import com.epam.volodko.entity.user.Role;
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

public class RegistrationCommand extends RequestSaver implements Command {

    private static final String REGISTRATION_MESSAGE_TEXT = "Now you are registered. Congrats!";
    private static final String PASSWORD_RESTRICT_MESS_TEXT = "Your password don't match the restrictions.";
    private static final String REGISTRATION_FAILED_MESS_TEXT = "Registration failed! Try again.";
    private static final String REGISTRATION_EXCEPTION_MESS_TEXT = "Registration can't be done. We working on this issue.";
    private static final String REGISTRATION_REDIRECT_COMMAND = String.format("%s?%s=%s&%s=%s",
            CommandName.CONTROLLER, CommandName.COMMAND, CommandName.GO_TO_MAIN_PAGE,
            ParameterName.REGISTRATION_MESSAGE, REGISTRATION_MESSAGE_TEXT);

    private final Logger log = LogManager.getLogger(RegistrationCommand.class);
    private final UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        saveRequest(request);
        try {
            if (validatePassword(request)){
                registration(request, response);
            } else {
                forwardOnFailedRegistration(request, response, PASSWORD_RESTRICT_MESS_TEXT);
            }
        } catch (ServiceException e) {
            log.error("Catching:", e);
            forwardOnFailedRegistration(request, response, REGISTRATION_EXCEPTION_MESS_TEXT);
        }
    }

    private void registration(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, IOException, ServletException {
        User user = prepareUserForSave(request);
        if (userService.processRegistration(user)) {
            HttpSession session = request.getSession();
            session.setAttribute(ParameterName.USER_LOGIN, user.getId());
            session.setAttribute(ParameterName.USER_ROLE, user.getRole());
            response.sendRedirect(REGISTRATION_REDIRECT_COMMAND);
        } else {
            forwardOnFailedRegistration(request, response, REGISTRATION_FAILED_MESS_TEXT);
        }
    }

    private boolean validatePassword(HttpServletRequest request) throws ServiceException {
        String password = request.getParameter(ParameterName.USER_PASSWORD);
        String passwordRepeat = request.getParameter(ParameterName.USER_REPEAT_PASSWORD);
        return userService.processPasswordValidation(password, passwordRepeat);
    }

    private void forwardOnFailedRegistration(HttpServletRequest request, HttpServletResponse response, String message)
            throws ServletException, IOException {
        request.setAttribute(ParameterName.ERROR_MESSAGE, message);
        RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.REGISTRATION_PAGE);
        dispatcher.forward(request, response);
    }

    private User prepareUserForSave(HttpServletRequest request){
        String login = request.getParameter(ParameterName.USER_LOGIN);
        String password = request.getParameter(ParameterName.USER_PASSWORD);
        String name = request.getParameter(ParameterName.USER_NAME).trim();
        String phone = request.getParameter(ParameterName.USER_PHONE).trim();
        Role role = Role.valueOf(request.getParameter(ParameterName.USER_ROLE).toUpperCase());
        return new User(0, login, password, name, phone, role);
    }

}
