package com.playground.commerce.product.domain.vo;

import com.playground.commerce.product.domain.exception.InvalidStockException;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Stock {

    @Column(name = "stock", nullable = false)
    private Long value;

    protected Stock() {
    }

    public Stock(final Long stock) {
        validateStock(stock);
        this.value = stock;
    }

    private void validateStock(final Long stock) {
        if (stock < 0) {
            throw new InvalidStockException(stock);
        }
    }

    public Stock decrease(final long amount) {
        return new Stock(value - amount);
    }

    public Long getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Stock)) {
            return false;
        }
        final Stock stock = (Stock) o;
        return Objects.equals(getValue(), stock.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
