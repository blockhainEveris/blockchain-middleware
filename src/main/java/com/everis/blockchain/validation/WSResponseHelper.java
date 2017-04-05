package com.everis.blockchain.validation;

import com.everis.blockchain.exceptions.BlockChainException;
import com.everis.blockchain.exceptions.BlockChainValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Utility for building webservice responses.
 *
 * @author friosnar
 * @since 1.7
 */
public final class WSResponseHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(WSResponseHelper.class);
    private static final String DEFAULT_EXCEPTION = "DBEException";
    private static final String CODE = ".code";

    private WSResponseHelper() {
    }


    /**
     * Create a generic error response model instance.
     *
     * @param exception       Internal exception.
     * @param errorTranslator the error translator properties
     * @return A new instance of generic error response.
     */
    public static WSErrorResponse buildDBEErrorResponse(final BlockChainException exception, final Properties errorTranslator) {
        WSErrorResponse errorResponse = new WSErrorResponse();
        errorResponse.setError(translateErrorCode(exception, errorTranslator));
        errorResponse.setErrorDescription(translateErrorDescription(exception, errorTranslator));
        errorResponse.setErrorDetails(exception.getDescription());
        return errorResponse;
    }

    /**
     * Create a validation error response model instance.
     *
     * @param dbeValidationException Validation internal exception.
     * @param errorTranslator        error translator
     * @return A new instance of validation error response.
     */
    public static WSErrorResponse buildValidationErrorResponse(final BlockChainValidationException dbeValidationException,
                                                               final Properties errorTranslator) {
        WSValidationErrorResponse result = new WSValidationErrorResponse();
        result.setError(translateErrorCode(dbeValidationException, errorTranslator));
        result.setErrorDescription(translateErrorDescription(dbeValidationException, errorTranslator));
        List<WSValidationError> validationErrors = new ArrayList<>();
        Errors errors = dbeValidationException.getValidationErrors();

        if (errors != null) {
            StringBuilder descriptionBuilder;

            for (ObjectError oe : errors.getAllErrors()) {
                WSValidationError wsError = new WSValidationError();
                descriptionBuilder = new StringBuilder("");
                descriptionBuilder.append("Origin: ").append(oe.getObjectName());

                if (oe instanceof FieldError) {
                    FieldError fe = (FieldError) oe;
                    wsError.setType(fe.getCode());
                    wsError.setObject(fe.getField());
                    wsError.setDescription(fe.getDefaultMessage());
                    descriptionBuilder.append(", ").append("field: ").append(fe.getField());
                    descriptionBuilder.append(", ").append("rejected value: ").append(fe.getRejectedValue());
                    wsError.setDetails(descriptionBuilder.toString());
                } else {
                    wsError.setType("ObjectValidationError");
                    wsError.setObject(oe.getObjectName());
                    wsError.setDescription(oe.getDefaultMessage());
                    wsError.setDetails(descriptionBuilder.toString());
                }

                validationErrors.add(wsError);
            }
        }

        result.setErrorFields(validationErrors);
        return result;
    }

    /**
     * Translates the error code using the properties translator.
     *
     * @param exception       the exception to be translated
     * @param errorTranslator the properties translator
     * @return the error code translated
     */
    public static String translateErrorCode(final Exception exception, final Properties errorTranslator) {
        String errorCode = errorTranslator.getProperty(exception.getClass().getSimpleName() + CODE);
        if (errorCode == null) {
            LOGGER.warn("Could not find '{}' code in error-translator properties",
                    exception.getClass().getSimpleName());
            errorCode = errorTranslator.getProperty(DEFAULT_EXCEPTION + CODE);
        }
        return errorCode;
    }

    /**
     * Translates the error description using the properties translator.
     *
     * @param exception       the exception to be translated
     * @param errorTranslator the properties translator
     * @return the error description translated
     */
    public static String translateErrorDescription(final Exception exception, final Properties errorTranslator) {
        String errorCode = errorTranslator.getProperty(exception.getClass().getSimpleName() + ".description");
        if (errorCode == null) {
            LOGGER.warn("Could not find '{}' description in error-translator properties",
                    exception.getClass().getSimpleName());
            errorCode = errorTranslator.getProperty(DEFAULT_EXCEPTION + CODE);
        }
        return errorCode;
    }

}
