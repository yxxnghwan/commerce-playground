package com.playground.commerce.product.domain.exception;

import com.playground.commerce.common.domain.exception.InvalidParameterException;

public final class InvalidPriceException extends InvalidParameterException {

    public InvalidPriceException(final Long price) {
        super(String.format("올바르지 않은 상품 가격입니다. price = %d", price));
    }
}
