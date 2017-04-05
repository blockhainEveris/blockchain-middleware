package com.everis.blockchain.validation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class WSErrorResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String error;

    @JsonProperty(value = "error_description")
    private String errorDescription;

    /**
     * Details.
     */
    @JsonIgnore
    private String errorDetails;

    /**
     * Gets the error.
     *
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * Sets the error.
     *
     * @param error the new error
     */
    public void setError(final String errorValue) {
        this.error = errorValue;
    }

    /**
     * Gets the error description.
     *
     * @return the error description
     */
    public String getErrorDescription() {
        return errorDescription;
    }

    /**
     * Sets the error description.
     *
     * @param value the new value
     */
    public void setErrorDescription(final String errorDescriptionValue) {
        this.errorDescription = errorDescriptionValue;
    }

    /**
     * Gets the error details.
     *
     * @return the error details
     */
    public String getErrorDetails() {
        return errorDetails;
    }

    /**
     * Sets the value.
     *
     * @param value the new value
     */
    public void setErrorDetails(final String errorDetailsValue) {
        this.errorDetails = errorDetailsValue;
    }

}
