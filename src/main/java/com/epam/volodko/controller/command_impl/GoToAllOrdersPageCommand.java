package com.epam.volodko.controller.command_impl;

import com.epam.volodko.controller.Command;
import com.epam.volodko.controller.constant.Message;
import com.epam.volodko.controller.constant.PagePath;
import com.epam.volodko.controller.constant.ParameterName;
import com.epam.volodko.dao.DAOFactory;
import com.epam.volodko.dao.OrderDAO;
import com.epam.volodko.dao.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToAllOrdersPageCommand extends RequestSaver implements Command {

    private static final Logger log = LogManager.getLogger(GoToAllOrdersPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        saveRequest(request);

        // TODO: 04.01.2022 get all orders from OrderService
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        try {
            request.setAttribute(ParameterName.ORDERS_LIST, orderDAO.findAll());
        } catch (DAOException e) {
            log.error("Catching: ", e);
            request.setAttribute(ParameterName.ERROR_MESSAGE, Message.ORDER_LOADING_FAILED);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.ALL_ORDERS_PAGE);
        dispatcher.forward(request, response);
    }
}
