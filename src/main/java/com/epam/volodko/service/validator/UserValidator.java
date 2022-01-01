package com.epam.volodko.service.validator;

import com.epam.volodko.entity.user.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator extends AbstractValidator{

    private static final String PHONE_PATTERN = "\\+?\\d{1,15}";
    private static final String PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[a-zA-Z])[0-9a-zA-Z]{5,}";

    public boolean validateLoginAndPassword(String login, String password){
        return notEmptyString(login) && notEmptyString(password);
    }

    public boolean validateRegistration(User user) {
        return validateLoginAndPassword(user.getLogin(), user.getPassword()) &&
                validatePhone(user.getPhone());
    }

    private boolean validatePhone(String phone) {
        if (!notEmptyString(phone)){
            return true;
        }
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public boolean validatePasswordRepeat(String password, String passwordRepeat) {
        return notEmptyString(password) &&
                validatePasswordRestrictions(password) &&
                password.equals(passwordRepeat);
    }

    public boolean validatePasswordRestrictions(String password){
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

}
