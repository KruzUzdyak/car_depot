package com.epam.volodko.controller;

import com.epam.volodko.controller.constant.Message;
import com.epam.volodko.controller.constant.PagePath;
import com.epam.volodko.controller.constant.ParameterName;
import com.epam.volodko.entity.user.Role;
import com.epam.volodko.service.ServiceFactory;
import com.epam.volodko.service.UserService;
import com.epam.volodko.service.exception.ServiceException;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

public interface Command {

    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    default void forward(HttpServletRequest request, HttpServletResponse response, String pagePath)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(pagePath);
        dispatcher.forward(request, response);
    }

    default void forwardToUserCabinet(HttpServletRequest request, HttpServletResponse response, String errorMessage, Logger log)
            throws ServletException, IOException {
        request.setAttribute(ParameterName.ERROR_MESSAGE, errorMessage);

        try {
            setUserInfo(request);
        } catch (ServiceException e) {
            log.error("Catching: ", e);
            request.setAttribute(ParameterName.ERROR_MESSAGE, Message.USER_INFO_LOAD_FAILED);
        }
        forward(request, response, PagePath.USER_CABINET_PAGE);
    }

    default void setUserInfo(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute(ParameterName.USER_ID);
        Role role = (Role) session.getAttribute(ParameterName.USER_ROLE);
        UserService service = ServiceFactory.getInstance().getUserService();
        request.setAttribute(ParameterName.USER, service.getUser(userId, role));
    }

    default void forwardOnFail(HttpServletRequest request, HttpServletResponse response, String pagePath, String errorMessage)
            throws ServletException, IOException {
        request.setAttribute(ParameterName.ERROR_MESSAGE, errorMessage);
        RequestDispatcher dispatcher = request.getRequestDispatcher(pagePath);
        dispatcher.forward(request, response);
    }
}
