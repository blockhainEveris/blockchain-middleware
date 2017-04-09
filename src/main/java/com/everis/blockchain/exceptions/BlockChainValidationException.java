package com.everis.blockchain.exceptions;

import org.springframework.validation.Errors;

public class BlockChainValidationException extends BlockChainException {

    private static final long serialVersionUID = 1L;

    private static final String DEFAULT_MESSAGE = "Validation exception";

    private final Errors validationErrors;

    public BlockChainValidationException(final Throwable cause) {
        super(cause);
        this.validationErrors = null;
    }

    public BlockChainValidationException() {
        super(DEFAULT_MESSAGE);
        this.validationErrors = null;
    }

    public BlockChainValidationException(final Errors errors) {
        super(DEFAULT_MESSAGE);
        this.validationErrors = errors;
    }

    public BlockChainValidationException(final Exception e, final Errors errors) {
        super(DEFAULT_MESSAGE, e);
        this.validationErrors = errors;
    }

    public Errors getValidationErrors() {
        return validationErrors;
    }

}
