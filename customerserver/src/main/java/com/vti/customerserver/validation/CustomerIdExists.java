package com.vti.customerserver.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(
        validatedBy = CustomerIdExistsValidator.class
)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)

public @interface CustomerIdExists {
    String message() default "Customer id does not exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
