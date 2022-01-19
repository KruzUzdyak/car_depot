package com.epam.volodko.controller.impl.user_cabinet;

import com.epam.volodko.controller.Command;
import com.epam.volodko.controller.constant.CommandName;
import com.epam.volodko.controller.constant.Message;
import com.epam.volodko.controller.constant.ParameterName;
import com.epam.volodko.entity.user.*;
import com.epam.volodko.service.ServiceFactory;
import com.epam.volodko.service.UserService;
import com.epam.volodko.service.exception.ServiceException;
import com.epam.volodko.service.util.DateFormatter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

public class UpdateInfoCommand implements Command {

    private final static String UPDATE_LOGIN_REDIRECT_COMMAND = String.format(
            "%s?%s=%s&%s=%s",
            CommandName.CONTROLLER, CommandName.COMMAND, CommandName.GO_TO_USER_CABINET_PAGE,
            ParameterName.MESSAGE, Message.UPDATE_INFO_SUCCESSFUL);

    private final Logger log = LogManager.getLogger(UpdateInfoCommand.class);
    private final UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            User user = prepareUserUpdate(request);
            if (userService.updateUser(user)){
                response.sendRedirect(UPDATE_LOGIN_REDIRECT_COMMAND);
            } else {
                forwardToUserCabinet(request, response, Message.UPDATE_INFO_FAILED, log);
            }
        } catch (ParseException | ServiceException e) {
            log.error("Catching: ", e);
            forwardToUserCabinet(request, response, Message.UPDATE_INFO_FAILED, log);
        }
    }

    private User prepareUserUpdate(HttpServletRequest request) throws ParseException {
        User user = null;
        Role role = (Role) request.getSession().getAttribute(ParameterName.USER_ROLE);
        switch (role){
            case ADMIN -> user = prepareAdmin(request);
            case CLIENT -> user = prepareClient(request);
            case DRIVER -> user = prepareDriver(request);
        }
        return user;
    }

    private User prepareAdmin(HttpServletRequest request) throws ParseException {
        Admin admin = new Admin();
        admin.setId((int) request.getSession().getAttribute(ParameterName.USER_ID));
        admin.setName(request.getParameter(ParameterName.USER_NAME));
        admin.setPhone(request.getParameter(ParameterName.USER_PHONE));
        admin.setWorksSince(DateFormatter.format(request.getParameter(ParameterName.ADMIN_WORKS_SINCE)));
        admin.setNote(request.getParameter(ParameterName.ADMIN_NOTE));
        return admin;
    }

    private User prepareClient(HttpServletRequest request) {
        Client client = new Client();
        client.setId((int) request.getSession().getAttribute(ParameterName.USER_ID));
        client.setName(request.getParameter(ParameterName.USER_NAME));
        client.setPhone(request.getParameter(ParameterName.USER_PHONE));
        client.setCompany(request.getParameter(ParameterName.CLIENT_COMPANY));
        client.setNote(request.getParameter(ParameterName.CLIENT_NOTE));
        return client;
    }

    private User prepareDriver(HttpServletRequest request) {
        Driver driver = new Driver();
        driver.setId((int) request.getSession().getAttribute(ParameterName.USER_ID));
        driver.setName(request.getParameter(ParameterName.USER_NAME));
        driver.setPhone(request.getParameter(ParameterName.USER_PHONE));
        return driver;
    }
}
