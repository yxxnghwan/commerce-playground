package com.playground.commerce.order.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.playground.commerce.order.domain.exception.InvalidQuantityException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OrderItemTest {

    @Test
    @DisplayName("주문 항목 객체를 생성한다.")
    void createOrderItem() {
        final OrderItem orderItem = new OrderItem("1", "테스트상품", 10L, 5000L);

        assertAll(
                () -> assertThat(orderItem.getProductNumber()).isEqualTo("1"),
                () -> assertThat(orderItem.getProductName()).isEqualTo("테스트상품"),
                () -> assertThat(orderItem.getQuantity()).isEqualTo(10L),
                () -> assertThat(orderItem.getItemPricePerPiece()).isEqualTo(5000L)
        );
    }

    @Test
    @DisplayName("음수의 수량으로 객체를 생성할 수 없다.")
    void createOrderItemWithNegativeQuantity() {
        assertThatThrownBy(() -> new OrderItem("1", "테스트상품", -1L, 5000L))
                .isInstanceOf(InvalidQuantityException.class)
                .hasMessageContaining(String.valueOf(-1L));
    }

    @Test
    @DisplayName("주문 항목 총액을 계산한다.")
    void getPrice() {
        final OrderItem orderItem = new OrderItem("1", "테스트상품", 10L, 5000L);

        assertThat(orderItem.getPrice()).isEqualTo(50000L);
    }

    @ParameterizedTest
    @CsvSource(value = {"1,1,2", "2,1,3"})
    @DisplayName("수량을 상승시킨다.")
    void addQuantity(final long quantity, final long addAmount, final long expected) {
        final OrderItem orderItem = new OrderItem("1", "테스트상품", quantity, 5000L);

        orderItem.addQuantity(addAmount);

        assertThat(orderItem.getQuantity()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"1,-2", "2,-3"})
    @DisplayName("음수의 수량을 더했을 때 수량이 0보다 작을 수 없다.")
    void addQuantityNegative(final long quantity, final long addAmount) {
        final OrderItem orderItem = new OrderItem("1", "테스트상품", quantity, 5000L);

        assertThatThrownBy(() -> orderItem.addQuantity(addAmount))
                .isInstanceOf(InvalidQuantityException.class)
                .hasMessageContaining(String.valueOf(quantity + addAmount));
    }

    @Test
    @DisplayName("상품 번호가 같으면 같은 상품으로 판단한다.")
    void isSameProduct() {
        final OrderItem orderItem1 = new OrderItem("1", "테스트상품", 10L, 5000L);
        final OrderItem orderItem2 = new OrderItem("1", "테스트상품", 15L, 5000L);

        assertThat(orderItem1.isSameProduct(orderItem2)).isTrue();
    }
}
