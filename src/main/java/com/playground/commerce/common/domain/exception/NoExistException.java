package com.playground.commerce.common.domain.exception;

public abstract class NoExistException extends ProductOrderException {

    public NoExistException(final String message) {
        super(message);
    }
}
