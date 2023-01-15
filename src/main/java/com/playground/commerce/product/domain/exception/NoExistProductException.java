package com.playground.commerce.product.domain.exception;

import com.playground.commerce.common.domain.exception.NoExistException;

public final class NoExistProductException extends NoExistException {

    public NoExistProductException(final String productNumber) {
        super(String.format("존재하지 않는 상품 번호입니다. productNumber = %s", productNumber));
    }
}
