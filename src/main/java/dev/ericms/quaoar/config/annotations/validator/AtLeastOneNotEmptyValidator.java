package dev.ericms.quaoar.config.annotations.validator;

import dev.ericms.quaoar.config.annotations.annotation.AtLeastOneNotEmpty;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

import java.util.List;

public class AtLeastOneNotEmptyValidator implements ConstraintValidator<AtLeastOneNotEmpty, Object> {

    private String[] fields;
    private String message;

    @Override
    public void initialize(AtLeastOneNotEmpty constraintAnnotation) {
        this.fields = constraintAnnotation.fields();
        this.message = constraintAnnotation.message();
    }

    @Override public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        boolean isValid = false;
        for (String fieldName : fields) {
            Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(fieldName);
            if (fieldValue != null && !((fieldValue instanceof List) && ((List<?>) fieldValue).isEmpty())) {
                isValid = true;
                break;
            }
        }

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(fields[0])
                    .addConstraintViolation();
        }

        return isValid;
    }
}
