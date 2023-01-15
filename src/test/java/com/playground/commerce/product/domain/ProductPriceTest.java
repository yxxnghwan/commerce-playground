package com.playground.commerce.product.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.playground.commerce.product.domain.exception.InvalidPriceException;
import com.playground.commerce.product.domain.vo.ProductPrice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ProductPriceTest {

    @Test
    @DisplayName("가격 객체를 생성한다.")
    void createPrice() {
        final ProductPrice productPrice = new ProductPrice(1L);

        assertThat(productPrice.getValue()).isEqualTo(1L);
    }

    @ParameterizedTest
    @ValueSource(longs = {0L, -1L})
    @DisplayName("0 이하의 가격은 존재할 수 없다.")
    void createPriceWithInvalidPrice(final long invalidPrice) {
        assertThatThrownBy(() -> new ProductPrice(invalidPrice))
                .isInstanceOf(InvalidPriceException.class)
                .hasMessageContaining(String.valueOf(invalidPrice));
    }

    @Test
    @DisplayName("value 값이 같으면 같은 객체로 판단한다.")
    void testEquals() {
        final ProductPrice productPrice1 = new ProductPrice(1L);
        final ProductPrice productPrice2 = new ProductPrice(1L);

        assertThat(productPrice1.equals(productPrice2)).isTrue();
    }
}