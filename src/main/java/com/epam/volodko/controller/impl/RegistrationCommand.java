package com.epam.volodko.controller.impl;

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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistrationCommand implements Command {

    private static final String REGISTRATION_REDIRECT_COMMAND = String.format("%s?%s=%s&%s=%s",
            CommandName.CONTROLLER, CommandName.COMMAND, CommandName.GO_TO_MAIN_PAGE,
            ParameterName.GREETING_MESSAGE, Message.REGISTRATION_SUCCESSFUL);

    private final Logger log = LogManager.getLogger(RegistrationCommand.class);
    private final UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if (validatePassword(request)){
                registration(request, response);
            } else {
                log.info("Registration failed - password not valid.");
                forwardOnFail(request, response, PagePath.REGISTRATION_PAGE, Message.PASSWORD_RESTRICTION_WARN);
            }
        } catch (ServiceException e) {
            log.error("Catching:", e);
            forwardOnFail(request, response, PagePath.REGISTRATION_PAGE, Message.REGISTRATION_EXCEPTION);
        }
    }

    private void registration(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, IOException, ServletException {
        User user = prepareUserForSave(request);
        if (userService.processRegistration(user)) {
            HttpSession session = request.getSession();
            if (session.getAttribute(ParameterName.USER_ROLE) == null){
                session.setAttribute(ParameterName.USER_ID, user.getId());
                session.setAttribute(ParameterName.USER_ROLE, user.getRole());
            }
            response.sendRedirect(REGISTRATION_REDIRECT_COMMAND);
        } else {
            log.info("Registration failed - wrong user data.");
            forwardOnFail(request, response, PagePath.REGISTRATION_PAGE, Message.REGISTRATION_FAILED);
        }
    }

    private boolean validatePassword(HttpServletRequest request) throws ServiceException {
        String password = request.getParameter(ParameterName.USER_PASS);
        String passwordRepeat = request.getParameter(ParameterName.USER_PASS_REPEAT);
        return userService.processPasswordValidation(password, passwordRepeat);
    }

    private User prepareUserForSave(HttpServletRequest request){
        String login = request.getParameter(ParameterName.USER_LOGIN);
        String passwordHash = userService.encodePassword(request.getParameter(ParameterName.USER_PASS));
        String name = request.getParameter(ParameterName.USER_NAME).trim();
        String phone = request.getParameter(ParameterName.USER_PHONE).trim();
        Role role = Role.valueOf(request.getParameter(ParameterName.REGISTER_ROLE).toUpperCase());
        return new User(0, login, passwordHash, name, phone, role);
    }

}
