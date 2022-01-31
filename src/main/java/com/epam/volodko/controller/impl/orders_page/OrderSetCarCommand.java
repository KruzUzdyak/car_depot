package com.epam.volodko.controller.impl.orders_page;

import com.epam.volodko.controller.Command;
import com.epam.volodko.controller.constant.CommandName;
import com.epam.volodko.controller.constant.Message;
import com.epam.volodko.controller.constant.PagePath;
import com.epam.volodko.controller.constant.ParameterName;
import com.epam.volodko.service.OrderService;
import com.epam.volodko.service.ServiceFactory;
import com.epam.volodko.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderSetCarCommand implements Command {

    private static final String SET_CAR_REDIRECT_COMMAND = String.format("%s?%s=%s&%s=",
            CommandName.CONTROLLER, CommandName.COMMAND, CommandName.GO_TO_ORDER_INFO_PAGE,
            ParameterName.ORDER_ID);
    private static final String ORDER_UPDATE_FAILED_LOG = "Can't set car id=%d to order id=%d.";
    private static final String ORDER_SET_CAR_LOG = "Set car id=%d to order id=%s";

    private final Logger log = LogManager.getLogger(OrderSetCarCommand.class);
    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            setCarToOrder(request, response);
        } catch (ServiceException e){
            log.error("Catching: ", e);
            forwardOnFail(request, response, PagePath.ORDER_INFO_PAGE, Message.UPDATE_ORDER_ERROR);
        }
    }

    private void setCarToOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, IOException, ServletException {
        int orderId = Integer.parseInt(request.getParameter(ParameterName.ORDER_ID));
        int carId = Integer.parseInt(request.getParameter(ParameterName.ORDER_CAR_ID));

        if (orderService.setCar(orderId, carId)) {
            log.info(String.format(ORDER_SET_CAR_LOG, carId, orderId));
            response.sendRedirect(String.format("%s%d", SET_CAR_REDIRECT_COMMAND, orderId));
        } else {
            log.warn(String.format(ORDER_UPDATE_FAILED_LOG, carId, orderId));
            forwardOnFail(request, response, PagePath.ORDER_INFO_PAGE, Message.UPDATE_ORDER_FAILED);
        }
    }
}
