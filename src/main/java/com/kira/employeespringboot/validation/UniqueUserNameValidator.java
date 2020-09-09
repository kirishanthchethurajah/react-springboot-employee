package com.kira.employeespringboot.validation;

import com.kira.employeespringboot.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName, String> {

    private UserRepository userRepository;

    public UniqueUserNameValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public boolean isValid(String userName, ConstraintValidatorContext constraintValidatorContext) {
        return userName!=null && userRepository.findByUserName(userName)== null;
    }
}
