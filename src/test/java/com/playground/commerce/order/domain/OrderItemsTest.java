package com.playground.commerce.order.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderItemsTest {

    @Test
    @DisplayName("중복된 상품이 들어올 시 주문 량을 더해서 합친다.")
    void createWithDuplicateOrderItem() {
        final OrderItem orderItem1 = new OrderItem("1", "테스트상품1", 10L, 5000L);
        final OrderItem orderItem2 = new OrderItem("2", "테스트상품2", 10L, 3000L);
        final OrderItem orderItem1_1 = new OrderItem("1", "테스트상품1", 10L, 5000L);
        final OrderItem orderItem2_1 = new OrderItem("2", "테스트상품2", 5L, 3000L);

        final OrderItems orderItems = new OrderItems(List.of(orderItem1, orderItem2, orderItem1_1, orderItem2_1));
        final List<OrderItem> orderItemsValues = orderItems.getValues();
        final OrderItem mergedOrderItem1 = orderItemsValues.stream()
                .filter(orderItem -> orderItem.getProductNumber().equals("1"))
                .findAny()
                .orElseThrow();
        final OrderItem mergedOrderItem2 = orderItemsValues.stream()
                .filter(orderItem -> orderItem.getProductNumber().equals("2"))
                .findAny()
                .orElseThrow();

        assertAll(
                () -> assertThat(orderItemsValues).hasSize(2),
                () -> assertThat(orderItemsValues).extracting("productNumber")
                        .containsOnly("1", "2"),
                () -> assertThat(mergedOrderItem1.getQuantity()).isEqualTo(20L),
                () -> assertThat(mergedOrderItem2.getQuantity()).isEqualTo(15L)
        );
    }

    @Test
    @DisplayName("모든 주문 항목의 총 가격을 계산한다.")
    void getTotalPrice() {
        final OrderItem orderItem1 = new OrderItem("1", "테스트상품1", 10L, 5000L);
        final OrderItem orderItem2 = new OrderItem("2", "테스트상품2", 10L, 3000L);
        final OrderItem orderItem3 = new OrderItem("3", "테스트상품3", 10L, 2000L);
        final OrderItems orderItems = new OrderItems(List.of(orderItem1, orderItem2, orderItem3));

        assertThat(orderItems.getTotalPrice()).isEqualTo(100_000L);
    }
}
