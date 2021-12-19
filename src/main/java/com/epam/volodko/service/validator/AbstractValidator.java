package com.epam.volodko.service.validator;

public abstract class AbstractValidator {

    boolean notEmptyString(String string){
        return string != null && !string.isEmpty();
    }
}
