package com.epam.volodko.service.validator;

import com.epam.volodko.entity.order.Order;

import java.util.Date;

public class OrderValidator extends AbstractValidator{

    public boolean validateForSave(Order order){

        return notEmptyString(order.getDestFrom()) &&
                notEmptyString(order.getDestTo()) &&
                notEmptyString(order.getLoadNote()) &&
                order.getDistance() > 0 &&
                isDateCorrect(order.getDateStart(), order.getDateEnd()) &&
                order.getLoad() > 0 &&
                order.getPayment() > 0 &&
                order.getClient() != null &&
                !order.isCompleted();
    }

    private boolean isDateCorrect(Date dateStart, Date dateEnd) {
        return dateStart.compareTo(new Date()) >= 0 &&
                dateEnd.compareTo(dateStart) > 0;
    }


}
