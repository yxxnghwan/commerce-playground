package com.playground.commerce.common.domain.exception;

public abstract class ProductOrderException extends RuntimeException {

    public ProductOrderException(final String message) {
        super(message);
    }
}
