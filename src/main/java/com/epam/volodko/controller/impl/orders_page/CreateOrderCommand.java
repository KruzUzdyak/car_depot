package com.epam.volodko.controller.impl.orders_page;

import com.epam.volodko.controller.Command;
import com.epam.volodko.controller.constant.CommandName;
import com.epam.volodko.controller.constant.Message;
import com.epam.volodko.controller.constant.PagePath;
import com.epam.volodko.controller.constant.ParameterName;
import com.epam.volodko.entity.order.Order;
import com.epam.volodko.entity.user.Client;
import com.epam.volodko.entity.user.Role;
import com.epam.volodko.service.OrderService;
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
import java.util.Date;

public class CreateOrderCommand implements Command {

    private static final String CREATE_ORDER_REDIRECT_COMMAND = String.format("%s?%s=%s&%s=",
            CommandName.CONTROLLER, CommandName.COMMAND, CommandName.GO_TO_ORDER_INFO_PAGE,
             ParameterName.ORDER_ID);

    private final Logger log = LogManager.getLogger(CreateOrderCommand.class);
    private final UserService userService = ServiceFactory.getInstance().getUserService();
    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Order order = prepareOrder(request);
            if (orderService.saveOrder(order)){
                response.sendRedirect(String.format("%s%d", CREATE_ORDER_REDIRECT_COMMAND, order.getId()));
            } else{
                log.warn("New order saving failed");
                forwardOnFail(request, response, PagePath.ORDER_CREATE_PAGE, Message.CREATE_ORDER_FAILED);
            }
        } catch (ParseException e) {
            log.error("Catching: ", e);
            forwardOnFail(request, response, PagePath.ORDER_CREATE_PAGE, Message.INVALID_DATE);
        } catch (ServiceException e) {
            log.error("Catching: ", e);
            forwardOnFail(request, response, PagePath.ORDER_CREATE_PAGE, Message.CREATE_ORDER_ERROR);
        }
    }

    private Order prepareOrder(HttpServletRequest request) throws ParseException, ServiceException {
        String destFrom = request.getParameter(ParameterName.ORDER_DEST_FROM);
        String destTo = request.getParameter(ParameterName.ORDER_DEST_TO);
        int distance = Integer.parseInt(request.getParameter(ParameterName.ORDER_DISTANCE));
        Date dateStart = DateFormatter.format(request.getParameter(ParameterName.ORDER_DATE_START));
        Date dateEnd = DateFormatter.format(request.getParameter(ParameterName.ORDER_DATE_END));
        int load = Integer.parseInt(request.getParameter(ParameterName.ORDER_LOAD));
        String loadNote = request.getParameter(ParameterName.ORDER_LOAD_NOTE);
        int payment = Integer.parseInt(request.getParameter(ParameterName.ORDER_PAYMENT));
        boolean completed = false;
        int clientId = (int) request.getSession().getAttribute(ParameterName.USER_ID);
        Client client = (Client) userService.getUser(clientId, Role.CLIENT);
        return new Order(0, destFrom, destTo, distance, dateStart, dateEnd, load, loadNote, completed,
                payment, client, null, null);
    }
}
