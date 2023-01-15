package com.playground.commerce.common.domain.exception;

public abstract class InvalidParameterException extends ProductOrderException {

    public InvalidParameterException(final String message) {
        super(message);
    }
}
