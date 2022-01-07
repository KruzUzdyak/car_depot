package com.epam.volodko.controller.command_impl;

import com.epam.volodko.controller.Command;
import com.epam.volodko.controller.constant.Message;
import com.epam.volodko.controller.constant.PagePath;
import com.epam.volodko.controller.constant.ParameterName;
import com.epam.volodko.entity.user.Role;
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

public class GoToUserCabinetPage implements Command {

    private final Logger log = LogManager.getLogger(GoToUserCabinetPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        checkUserLoggedIn(request, response);
        saveRequest(request);

        try{
            setUserInfo(request);
        } catch (ServiceException e) {
            log.error("Catching: ", e);
            request.setAttribute(ParameterName.ERROR_MESSAGE, Message.USER_INFO_LOAD_FAILED);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.USER_CABINET_PAGE);
        dispatcher.forward(request, response);
    }

    private void setUserInfo(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute(ParameterName.USER_ID);
        Role role = (Role) session.getAttribute(ParameterName.USER_ROLE);
        UserService service = ServiceFactory.getInstance().getUserService();
        request.setAttribute(ParameterName.USER, service.getUser(userId, role));
    }
}
