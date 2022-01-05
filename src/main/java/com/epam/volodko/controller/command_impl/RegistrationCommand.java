package com.epam.volodko.controller.command_impl;

import com.epam.volodko.controller.Command;
import com.epam.volodko.controller.constant.CommandName;
import com.epam.volodko.controller.constant.Message;
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

public class RegistrationCommand extends AbstractCommand implements Command {

    private static final String REGISTRATION_REDIRECT_COMMAND = String.format("%s?%s=%s&%s=%s",
            CommandName.CONTROLLER, CommandName.COMMAND, CommandName.GO_TO_MAIN_PAGE,
            ParameterName.GREETING_MESSAGE, Message.REGISTRATION_SUCCESSFUL);

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
                log.info("Registration failed - password not valid.");
                forwardOnFailedRegistration(request, response, Message.PASSWORD_RESTRICTION_WARN);
            }
        } catch (ServiceException e) {
            log.error("Catching:", e);
            forwardOnFailedRegistration(request, response, Message.REGISTRATION_EXCEPTION);
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
            log.info("Registration failed - wrong user data.");
            forwardOnFailedRegistration(request, response, Message.REGISTRATION_FAILED);
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
        String passwordHash = userService.encodePassword(request.getParameter(ParameterName.USER_PASSWORD));
        String name = request.getParameter(ParameterName.USER_NAME).trim();
        String phone = request.getParameter(ParameterName.USER_PHONE).trim();
        Role role = Role.valueOf(request.getParameter(ParameterName.USER_ROLE).toUpperCase());
        return new User(0, login, passwordHash, name, phone, role);
    }

}
