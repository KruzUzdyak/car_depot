package com.epam.volodko.service.validator;

import com.epam.volodko.entity.order.Order;
import com.epam.volodko.entity.user.Client;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class OrderValidatorTest {

    private final OrderValidator validator = new OrderValidator();

    @Test
    public void testValidateTrue() {
        Order order = createValidOrder();

        assertTrue(validator.validateForSave(order));
    }

    @Test
    public void testOrderInvalidDate(){
        Order order = createValidOrder();
        order.setDateEnd(new Date(1000L));

        assertFalse(validator.validateForSave(order));

        order = createValidOrder();

        order.setDateEnd(order.getDateStart());

        assertFalse(validator.validateForSave(order));
    }

    @Test
    public void testOrderInvalidClient(){
        Order order = createValidOrder();
        order.setClient(null);
        assertFalse(validator.validateForSave(order));
    }

    private Order createValidOrder(){
        Order order = new Order();
        order.setDestFrom("test dest from");
        order.setDestTo("test dest to");
        order.setCompleted(false);
        Date currentDate = new Date();
        order.setDateStart(new Date(currentDate.getTime() + 1000));
        order.setDateEnd(new Date(currentDate.getTime() + 2000));
        order.setDistance(10);
        order.setLoad(1000);
        order.setLoadNote("test note");
        order.setPayment(1000);
        order.setClient(new Client());
        return order;
    }

}