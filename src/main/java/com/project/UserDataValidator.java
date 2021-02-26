package com.project;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



@Component
public class UserDataValidator {

    public static boolean isValidEmail(String email){
        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }


    public static boolean isValidPassword(String password) {
        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(password);
        return matcher.find();
    }

}
