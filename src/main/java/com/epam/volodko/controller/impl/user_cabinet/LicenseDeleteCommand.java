package com.epam.volodko.controller.impl.user_cabinet;

import com.epam.volodko.controller.Command;
import com.epam.volodko.controller.constant.CommandName;
import com.epam.volodko.controller.constant.Message;
import com.epam.volodko.controller.constant.ParameterName;
import com.epam.volodko.service.ServiceFactory;
import com.epam.volodko.service.UserService;
import com.epam.volodko.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        int driverId = (int) request.getSession().getAttribute(ParameterName.USER_ID);
        int licenseTypeId = Integer.parseInt(request.getParameter(ParameterName.LICENSE_TYPE_ID));

        try{
            if (userService.deleteDriverLicense(driverId, licenseTypeId)){
                response.sendRedirect(LICENSE_DELETE_REDIRECT_COMMAND);
            }
        } catch (ServiceException e) {
            log.error("Catching: ", e);
            forwardToUserCabinet(request, response, Message.LICENSE_DELETE_FAILED, log);
        }
    }

}
