package com.kira.employeespringboot.validation;

import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.TYPE})
@Retention(RUNTIME)
public @interface PasswordConfirmed {
    String message() default  "Password do not match";
    Class <?>[] groups() default {};
    Class <? extends Payload>[] payload() default {};

}
