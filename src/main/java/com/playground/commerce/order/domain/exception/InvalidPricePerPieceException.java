package com.playground.commerce.order.domain.exception;

import com.playground.commerce.common.domain.exception.InvalidParameterException;

public final class InvalidPricePerPieceException extends InvalidParameterException {

    public InvalidPricePerPieceException(final Long pricePerPiece) {
        super(String.format("올바르지 않은 상품당 가격입니다. pricePerPiece = %d", pricePerPiece));
    }
}
