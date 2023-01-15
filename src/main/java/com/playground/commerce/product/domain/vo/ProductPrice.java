package com.playground.commerce.product.domain.vo;

import com.playground.commerce.product.domain.exception.InvalidPriceException;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProductPrice {

    @Column(name = "price", nullable = false)
    private Long value;

    protected ProductPrice() {
    }

    public ProductPrice(final Long price) {
        validatePrice(price);
        this.value = price;
    }

    private void validatePrice(final Long price) {
        if (price <= 0) {
            throw new InvalidPriceException(price);
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
        if (!(o instanceof ProductPrice)) {
            return false;
        }
        final ProductPrice that = (ProductPrice) o;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
