package com.playground.commerce.order.domain.exception;

import com.playground.commerce.common.domain.exception.ProductOrderException;

public final class InvalidQuantityException extends ProductOrderException {

    public InvalidQuantityException(final long quantity) {
        super(String.format("주문 수량이 음수일 수 없습니다. quantity = %d", quantity));
    }
}
