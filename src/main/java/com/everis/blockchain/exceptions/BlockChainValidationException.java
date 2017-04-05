package com.everis.blockchain.exceptions;

import org.springframework.validation.Errors;

/**
 * The DBE Validation Exception class.
 *
 * @author everis
 */
public class BlockChainValidationException extends BlockChainException {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1418389668379125471L;

    /**
     * The Constant DEFAULT_MESSAGE.
     */
    private static final String DEFAULT_MESSAGE = "Validation exception";

    /**
     * The validation errors.
     */
    private final Errors validationErrors;

    /**
     * Constructs a new exception with the specified cause and a detail message.
     *
     * @param cause the cause.
     */
    public BlockChainValidationException(final Throwable cause) {
        super(cause);
        this.validationErrors = null;
    }

    /**
     * Instantiates a new DBE validation exception.
     */
    public BlockChainValidationException() {
        super(DEFAULT_MESSAGE);
        this.validationErrors = null;
    }

    /**
     * Instantiates a new DBE validation exception.
     *
     * @param errors the exception errors
     */
    public BlockChainValidationException(final Errors errors) {
        super(DEFAULT_MESSAGE);
        this.validationErrors = errors;
    }

    /**
     * Instantiates a new DBE validation exception.
     *
     * @param e      the e
     * @param errors the exception errors
     */
    public BlockChainValidationException(final Exception e, final Errors errors) {
        super(DEFAULT_MESSAGE, e);
        this.validationErrors = errors;
    }

    /**
     * Gets the validation errors.
     *
     * @return the validationErrors
     */
    public Errors getValidationErrors() {
        return validationErrors;
    }

}
