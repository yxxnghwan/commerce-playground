package com.playground.commerce.product.domain.exception;

import com.playground.commerce.common.domain.exception.InvalidParameterException;

public final class InvalidStockException extends InvalidParameterException {

    public InvalidStockException(final Long stock) {
        super(String.format("올바르지 않은 상품 수량입니다. stock = %d", stock));
    }
}
