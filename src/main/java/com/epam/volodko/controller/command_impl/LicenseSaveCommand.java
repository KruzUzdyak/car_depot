package com.epam.volodko.controller.command_impl;

import com.epam.volodko.controller.Command;
import com.epam.volodko.controller.constant.CommandName;
import com.epam.volodko.controller.constant.Message;
import com.epam.volodko.controller.constant.ParameterName;
import com.epam.volodko.entity.user.DriverLicense;
import com.epam.volodko.entity.user.DriverLicenseType;
import com.epam.volodko.entity.user.LicenseTypeProvider;
import com.epam.volodko.service.ServiceFactory;
import com.epam.volodko.service.UserService;
import com.epam.volodko.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LicenseSaveCommand implements Command {

    private final static String LICENSE_SAVE_REDIRECT_COMMAND = String.format(
            "%s?%s=%s",
            CommandName.CONTROLLER, CommandName.COMMAND, CommandName.GO_TO_USER_CABINET_PAGE);

    private final Logger log = LogManager.getLogger(LicenseSaveCommand.class);
    private final UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        checkUserLoggedIn(request, response);
        saveRequest(request);

        try{
            processLicenseSaving(request, response);
        } catch (ParseException | ServiceException e) {
            log.error("Catching: ", e);
            forwardToUserCabinet(request, response, Message.LICENSE_SAVE_FAILED, log);
        }
    }

    private void processLicenseSaving(HttpServletRequest request, HttpServletResponse response)
            throws ParseException, ServiceException, IOException, ServletException {
        int driverId = (int) request.getSession().getAttribute(ParameterName.USER_ID);
        int licenseTypeId = Integer.parseInt(request.getParameter(ParameterName.LICENSE_TYPE_ID));
        DriverLicenseType licenseType = LicenseTypeProvider.getLicenseType(licenseTypeId);
        Date obtainingDate = parseDateFromRequest(request);
        String licenseNumber = request.getParameter(ParameterName.LICENSE_NUMBER);
        DriverLicense license = new DriverLicense(licenseType, obtainingDate, licenseNumber);
        if (userService.saveNewDriverLicense(driverId, license)){
            response.sendRedirect(LICENSE_SAVE_REDIRECT_COMMAND);
        } else {
            forwardToUserCabinet(request, response, Message.LICENSE_SAVE_FAILED, log);
        }
    }

    private Date parseDateFromRequest(HttpServletRequest request) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(request.getParameter(ParameterName.LICENSE_OBTAINING_DATE));
    }
}
