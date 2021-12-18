package com.epam.volodko.service.validator;

public class UserValidator extends AbstractValidator{

    public boolean validateLoginAndPassword(String login, String password){
        return notEmpty(login) && notEmpty(password);
    }


}
