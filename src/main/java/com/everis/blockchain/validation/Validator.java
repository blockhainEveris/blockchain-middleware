package com.everis.blockchain.validation;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Component
public class Validator implements org.springframework.validation.Validator, InitializingBean {

    /**
     * The validator.
     */
    private javax.validation.Validator validator;

    @Override
    public boolean supports(final Class<?> clazz) {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.usingContext().getValidator();
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        if (target == null) {
            errors.reject("Target is null");
        } else {

            Set<ConstraintViolation<Object>> constraintViolations = validator.validate(target);

            for (ConstraintViolation<Object> constraintViolation : constraintViolations) {

                String propertyPath = constraintViolation.getPropertyPath().toString();

                // Get error code from messageTemplate
                String messageTemplate = constraintViolation.getMessageTemplate();

                String errorCode = getErrorCode(messageTemplate);

                String message = constraintViolation.getMessage();

                errors.rejectValue(propertyPath, errorCode, message);
            }
        }

    }

    private String getErrorCode(final String messageTemplate) {

        String errorCode = "undefined";

        if (messageTemplate != null && messageTemplate.contains(".")) {
            String[] splitTemplate = messageTemplate.split("\\.");
            if (splitTemplate.length > 1) {
                errorCode = splitTemplate[splitTemplate.length - 2];
            }
        }
        return errorCode;
    }

}
