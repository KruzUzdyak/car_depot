package com.epam.volodko.controller.command_impl;

import com.epam.volodko.controller.Command;
import com.epam.volodko.controller.constant.CommandName;
import com.epam.volodko.controller.constant.Message;
import com.epam.volodko.controller.constant.PagePath;
import com.epam.volodko.controller.constant.ParameterName;
import com.epam.volodko.service.ServiceFactory;
import com.epam.volodko.service.UserService;
import com.epam.volodko.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LicenseDeleteCommand implements Command {

    private final static String LICENSE_DELETE_REDIRECT_COMMAND = String.format(
            "%s?%s=%s&%s=%s",
            CommandName.CONTROLLER, CommandName.COMMAND, CommandName.GO_TO_USER_CABINET_PAGE,
            ParameterName.MESSAGE, Message.LICENSE_DELETE_SUCCESSFUL);

    private final Logger log = LogManager.getLogger(LicenseDeleteCommand.class);
    private final UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        checkUserLoggedIn(request, response);
        saveRequest(request);

        int driverId = (int) request.getSession().getAttribute(ParameterName.USER_ID);
        int licenseTypeId = Integer.parseInt(request.getParameter(ParameterName.LICENSE_TYPE_ID));

        try{
            if (userService.deleteDriverLicense(driverId, licenseTypeId)){
                response.sendRedirect(LICENSE_DELETE_REDIRECT_COMMAND);
            }
        } catch (ServiceException e) {
            log.error("Catching: ", e);
            forwardOnFailedDelete(request, response, "temp");
        }
    }

    private void forwardOnFailedDelete(HttpServletRequest request, HttpServletResponse response, String message)
            throws ServletException, IOException {

        request.setAttribute(ParameterName.ERROR_MESSAGE, message);

        try {
            setUserInfo(request);
        } catch (ServiceException e) {
            log.error("Catching: ", e);
            request.setAttribute(ParameterName.ERROR_MESSAGE, Message.USER_INFO_LOAD_FAILED);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.USER_CABINET_PAGE);
        dispatcher.forward(request, response);
    }

}
