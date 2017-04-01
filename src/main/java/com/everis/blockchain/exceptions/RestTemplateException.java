package com.everis.blockchain.exceptions;


public class RestTemplateException extends Exception {

    public RestTemplateException(final String message, final Exception e) {
        super(message, e);
    }

    public RestTemplateException(final String message) {
        super(message);
    }

}
