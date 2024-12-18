package dev.ericms.quaoar.config.annotations.annotation;

import dev.ericms.quaoar.config.annotations.validator.AtLeastOneNotEmptyValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AtLeastOneNotEmptyValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AtLeastOneNotEmpty {

    String message() default "At least one of the fields must be filled";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String[] fields();
}