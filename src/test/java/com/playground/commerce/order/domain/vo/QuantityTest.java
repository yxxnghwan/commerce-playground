package com.playground.commerce.order.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.playground.commerce.order.domain.exception.InvalidQuantityException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QuantityTest {

    @Test
    @DisplayName("주문 수량 객체를 생성한다.")
    void createQuantity() {
        final Quantity quantity = new Quantity(10L);

        assertThat(quantity.getValue()).isEqualTo(10L);
    }

    @Test
    @DisplayName("주문 수량은 음수일 수 없다.")
    void createQuantityWithNegativeNumber() {
        assertThatThrownBy(() -> new Quantity(-1L))
                .isInstanceOf(InvalidQuantityException.class)
                .hasMessageContaining(String.valueOf(-1L));
    }

    @Test
    @DisplayName("수량을 더해서 새로운 객체를 만든다.")
    void addQuantity() {
        final Quantity quantity = new Quantity(10L);

        final Quantity addedQuantity = quantity.addQuantity(1L);

        assertThat(addedQuantity.getValue()).isEqualTo(11L);
    }

    @Test
    @DisplayName("수량을 더할 때 음수가 되도록 더할 수 없다.")
    void addQuantityForNegative() {
        final Quantity quantity = new Quantity(10L);

        assertThatThrownBy(() -> quantity.addQuantity(-11L))
                .isInstanceOf(InvalidQuantityException.class)
                .hasMessageContaining(String.valueOf(-1L));
    }

    @Test
    @DisplayName("value 값이 같으면 같은 객체로 판단한다.")
    void testEquals() {
        final Quantity quantity1 = new Quantity(10L);
        final Quantity quantity2 = new Quantity(10L);

        assertThat(quantity1.equals(quantity2)).isTrue();
    }
}