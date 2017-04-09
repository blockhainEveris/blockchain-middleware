package com.everis.blockchain.validation;

import java.util.ArrayList;
import java.util.List;

/**
 * Validation error response model for Webservice layer.
 *
 * @author friosnar
 * @since 1.7
 */
public class WSValidationErrorResponse extends WSErrorResponse {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The error_fields.
     */
    private List<WSValidationError> errorFields = new ArrayList<>();

    /**
     * Gets the error_fields.
     *
     * @return the error_fields
     */
    public List<WSValidationError> getErrorFields() {
        return errorFields;
    }

    /**
     * Sets the errorFields.
     *
     * @param errorFields the new errorFields
     */
    public void setErrorFields(final List<WSValidationError> errorFieldsValue) {
        this.errorFields = errorFieldsValue;
    }

}
