package com.epam.volodko.service.validator;

import com.epam.volodko.entity.user.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator extends AbstractValidator{

    private static final String PHONE_PATTERN = "\\+?\\d{1,15}";

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

    private boolean validatePasswordRestrictions(String password){
        return password.length() >= 5 && hasLetters(password.toCharArray())
                && hasDigits(password.toCharArray());
    }

    // TODO: 19.12.2021 REGEXP validation for password.
    private boolean hasLetters(char[] password){
        for (char c : password){
            if (Character.isLetter(c)){
                return true;
            }
        }
        return false;
    }

    private boolean hasDigits(char[] password) {
        for (char c : password) {
            if (Character.isDigit(c)){
                return true;
            }
        }
        return false;
    }
}
