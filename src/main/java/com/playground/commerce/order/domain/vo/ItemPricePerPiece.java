package com.playground.commerce.order.domain.vo;

import com.playground.commerce.order.domain.exception.InvalidPricePerPieceException;
import java.util.Objects;

public class ItemPricePerPiece {

    private final Long value;

    public ItemPricePerPiece(final long pricePerPiece) {
        validatePricePerPiece(pricePerPiece);
        this.value = pricePerPiece;
    }

    private void validatePricePerPiece(final Long pricePerPiece) {
        if (pricePerPiece <= 0) {
            throw new InvalidPricePerPieceException(pricePerPiece);
        }
    }

    public Long getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemPricePerPiece)) {
            return false;
        }
        final ItemPricePerPiece that = (ItemPricePerPiece) o;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
