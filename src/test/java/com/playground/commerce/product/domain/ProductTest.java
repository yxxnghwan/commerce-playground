package com.playground.commerce.product.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.playground.commerce.product.domain.exception.SoldOutException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    @DisplayName("상품을 생성한다.")
    void createItem() {
        final Product product = new Product(
                "768848",
                "[STANLEY] GO CERAMIVAC 진공 텀블러/보틀 3종",
                21000L,
                45L
        );

        assertAll(
                () -> assertThat(product.getProductNumber()).isEqualTo("768848"),
                () -> assertThat(product.getName()).isEqualTo("[STANLEY] GO CERAMIVAC 진공 텀블러/보틀 3종"),
                () -> assertThat(product.getPrice()).isEqualTo(21000L),
                () -> assertThat(product.getStock()).isEqualTo(45L)
        );
    }

    @Test
    @DisplayName("상품을 구매한다.")
    void sell() {
        final Product product = new Product(
                "768848",
                "[STANLEY] GO CERAMIVAC 진공 텀블러/보틀 3종",
                21000L,
                45L
        );

        product.sell(45L);

        assertThat(product.getStock()).isEqualTo(0L);
    }

    @Test
    @DisplayName("상품을 재고 이상으로 구매할 수 없다.")
    void sellOverAmount() {
        final Product product = new Product(
                "768848",
                "[STANLEY] GO CERAMIVAC 진공 텀블러/보틀 3종",
                21000L,
                45L
        );

        assertThatThrownBy(() -> product.sell(46L))
                .isInstanceOf(SoldOutException.class)
                .hasMessageContaining(String.valueOf(45L), String.valueOf(46L));
    }
}