package com.epam.volodko.service.validator;

public abstract class AbstractValidator {

    boolean notEmpty(String parameter){
        return parameter != null && !parameter.isEmpty();
    }
}
