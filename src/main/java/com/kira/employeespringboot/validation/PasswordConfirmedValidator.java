package com.kira.employeespringboot.validation;


import com.kira.employeespringboot.model.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordConfirmedValidator implements ConstraintValidator<PasswordConfirmed, Object> {
    @Override
    public boolean isValid(Object user, ConstraintValidatorContext constraintValidatorContext) {
        String password = ((User)user).getPassword();

        return ((User)user).getPassword().equals(((User)user).getPassword());
    }
}
