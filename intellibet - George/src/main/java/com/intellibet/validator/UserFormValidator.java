package com.intellibet.validator;

import com.intellibet.dto.UserForm;
import static org.apache.commons.lang3.StringUtils.*;
import static java.util.Objects.*;
import static org.apache.commons.lang3.math.NumberUtils.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class UserFormValidator implements Validator {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    @Override
    public boolean supports(Class<?> aClass ) {
        return UserForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserForm userForm = (UserForm) o;
        if(userForm.getFirstName()==null || userForm.getFirstName().length()<2 || userForm.getFirstName().length()>20){
            errors.rejectValue("firstName", "userForm.firstName.error");
        }
        if(userForm.getLastName()==null || userForm.getLastName().length()<2 || userForm.getLastName().length()>20){
            errors.rejectValue("lastName", "userForm.lastName.error");
        }
        if(!validateEmail(userForm.getEmail())){
            errors.rejectValue("email", "userForm.email.error");
        }
        if(isNull(userForm.getGender())){
            errors.rejectValue("gender", "userForm.gender.error");
        }
        if(isEmpty(userForm.getAddress())) {
            errors.rejectValue("address", "userForm.address.error");
            userForm.setPageSection("section-2");
        }
        if(isEmpty(userForm.getPostCode())){
            errors.rejectValue("postCode","userForm.postCode.error");
        }
        if(isEmpty(userForm.getCity())){
            errors.rejectValue("city","userForm.city.error");
        }
        if(isEmpty(userForm.getMobileNumber()) || !isDigits(userForm.getMobileNumber())){
            errors.rejectValue("mobileNumber","userForm.mobileNumber.error");
        }
        if(isEmpty(userForm.getPassword())){
            errors.rejectValue("password","userForm.password.error");
        }
    }
}
