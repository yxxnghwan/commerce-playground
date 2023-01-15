package com.playground.commerce.product.domain.exception;

import com.playground.commerce.common.domain.exception.InvalidParameterException;

public final class SoldOutException extends InvalidParameterException {

    public SoldOutException(final Long stock, final Long amount) {
        super(String.format("SoldOutException 발생. 주문한 상품량이 재고량보다 큽니다. stock = %d, amount = %d", stock, amount));
    }
}
