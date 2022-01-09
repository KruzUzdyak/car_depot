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

    default void saveRequest(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute(ParameterName.LAST_REQUEST, new HashMap<>(request.getParameterMap()));
    }

    default void checkUserLoggedIn(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Role role = (Role) request.getSession().getAttribute(ParameterName.USER_ROLE);
        if (role == null){
            request.setAttribute(ParameterName.ERROR_MESSAGE, Message.NOT_LOGGED_ID);
            RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.INITIAL_PAGE);
            dispatcher.forward(request, response);
        }
    }

    default void setUserInfo(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute(ParameterName.USER_ID);
        Role role = (Role) session.getAttribute(ParameterName.USER_ROLE);
        UserService service = ServiceFactory.getInstance().getUserService();
        request.setAttribute(ParameterName.USER, service.getUser(userId, role));
    }

    default void forwardToUserCabinet(HttpServletRequest request, HttpServletResponse response, String message, Logger log)
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
