package com.everis.blockchain.validation;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.List;

/**
 * DBE validation helper class.
 * @author everis
 */
public final class ValidationHelper {

    private ValidationHelper() {
    }

    /**
     * Validate input parameters checking not null objects.
     *
     * @param validator the validator
     * @param target the target
     * @return the errors
     */
    public static Errors validate(final Validator validator, final Object... target) {
        return validate(validator, true, target);
    }

    /**
     * Validate input parameters.
     *
     * @param validator the validator
     * @param checkNotNulls the check not nulls
     * @param target the target
     * @return the errors
     */
    public static Errors validate(final Validator validator, final boolean checkNotNulls,
            final Object... target) {

        // si alguno de los elementos es null se valida el error
        if (checkNotNulls) {
            for (Object item : target) {
                if (item == null) {
                    Errors errors = new BeanPropertyBindingResult(item, "");
                    validator.validate(item, errors);
                    return errors;
                }
            }
        }

        // elementos simples
        if (target.length == 1) {
            Errors errors = new BeanPropertyBindingResult(target[0], target[0].getClass().getName());
            validator.validate(target[0], errors);
            return errors;

            // listas
        } else {

            ValidationList validationList = new ValidationList();
            Errors errors = new BeanPropertyBindingResult(validationList, validationList.getClass().getName());

            List<Object> targetList = Arrays.asList(target);
            validationList.setItemList(targetList);

            validator.validate(validationList, errors);
            return errors;
        }

    }

}
