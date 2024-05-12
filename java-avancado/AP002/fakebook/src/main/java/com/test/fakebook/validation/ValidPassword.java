package com.test.fakebook.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

// Custom annotation for validating passwords
@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({ ElementType.METHOD,
          ElementType.FIELD,
          ElementType.ANNOTATION_TYPE,
          ElementType.CONSTRUCTOR,
          ElementType.PARAMETER,
          ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {

    // Default message for invalid passwords
    String message() default "Invalid password";

    // Groups for constraint validation
    Class<?>[] groups() default {};

    // Additional payload details
    Class<? extends Payload>[] payload() default {};
}
