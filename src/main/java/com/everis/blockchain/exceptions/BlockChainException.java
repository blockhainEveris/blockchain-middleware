package com.everis.blockchain.exceptions;

public class BlockChainException extends Exception {

    private static final long serialVersionUID = -4177832510285050290L;

    private final String description;

    public BlockChainException() {
        super();
        this.description = null;
    }

    public BlockChainException(final String message, final Throwable cause) {
        super(message, cause);
        this.description = buildDescription(cause);
    }

    public BlockChainException(final String message) {
        super(message);
        this.description = message;
    }

    public BlockChainException(final Throwable cause) {
        super(cause);
        this.description = buildDescription(cause);
    }


    public String getDescription() {
        return description;
    }

    public static String buildDescription(final Throwable e) {

        String description = null;

        if (e != null) {
            description = e.getMessage();

            if (e.getCause() != null) {
                description += " - " + e.getCause().getMessage();

                if (e.getCause().getCause() != null) {
                    description += " - " + e.getCause().getCause().getMessage();
                }
            }
        }

        return description;
    }

}
