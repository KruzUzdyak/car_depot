package com.epam.volodko.controller.impl;

import com.epam.volodko.controller.Command;
import com.epam.volodko.controller.constant.CommandName;
import com.epam.volodko.controller.constant.Message;
import com.epam.volodko.controller.constant.PagePath;
import com.epam.volodko.controller.constant.ParameterName;
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

public class LoginationCommand implements Command {


    private static final String LOGINATION_REDIRECT_COMMAND = String.format(
            "%s?%s=%s&%s=%s",
            CommandName.CONTROLLER, CommandName.COMMAND, CommandName.GO_TO_MAIN_PAGE,
            ParameterName.GREETING_MESSAGE, Message.LOGINATION_SUCCESSFUL);

    private final Logger log = LogManager.getLogger(LoginationCommand.class);
    private final UserService userService = ServiceFactory.getInstance().getUserService();
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter(ParameterName.USER_LOGIN);
        String password = request.getParameter(ParameterName.USER_PASS);
        User user;
        try {
            user = userService.processLogination(login, password);
            if (user != null){
                HttpSession session = request.getSession();
                session.setAttribute(ParameterName.USER_ID, user.getId());
                session.setAttribute(ParameterName.USER_ROLE, user.getRole());
                response.sendRedirect(LOGINATION_REDIRECT_COMMAND);
            } else {
                log.warn(String.format("Logination failed with user login - %s.", login));
                forwardOnFail(request, response, PagePath.LOGINATION_PAGE, Message.LOGINATION_FAILED);
            }
        } catch (ServiceException e) {
            log.error("Catching:", e);
            forwardOnFail(request, response, PagePath.LOGINATION_PAGE, Message.LOGINATION_EXCEPTION);
        }
    }




}
