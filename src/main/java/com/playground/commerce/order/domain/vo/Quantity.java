package com.playground.commerce.order.domain.vo;

import com.playground.commerce.order.domain.exception.InvalidQuantityException;
import java.util.Objects;

public class Quantity {

    private final Long value;

    public Quantity(final Long value) {
        validateQuantity(value);
        this.value = value;
    }

    private void validateQuantity(final long quantity) {
        if (quantity <= 0) {
            throw new InvalidQuantityException(quantity);
        }
    }

    public Quantity addQuantity(final long amount) {
        return new Quantity(value + amount);
    }

    public Long getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Quantity)) {
            return false;
        }
        final Quantity quantity = (Quantity) o;
        return Objects.equals(getValue(), quantity.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
