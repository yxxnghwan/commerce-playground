package com.playground.commerce.order.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import com.playground.commerce.ServiceTest;
import com.playground.commerce.product.application.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

class OrderServiceTest extends ServiceTest {

    @MockBean
    private ProductService productService;

    @Test
    @DisplayName("주문 정보로 주문을 요청한다")
    void order() {
        final var 주문_결과 = 독립된_실행(status -> 주문_정보로_주문을_요청한다(
                주문_정보(아노락_상품번호, "아노락", 1_000L, 2L),
                주문_정보(후리스_상품번호, "후리스", 5_000L, 2L),
                주문_정보(롱패딩_상품번호, "롱패딩", 10_000L, 2L)
        ));

        assertAll(
                () -> assertThat(주문_결과.getOrderAmount()).isEqualTo(32_000L),
                () -> assertThat(주문_결과.getPaymentAmount()).isEqualTo(34_500L),
                () -> assertThat(주문_결과.getOrderItemResultDtos()).extracting("productName")
                        .containsOnly("아노락", "후리스", "롱패딩"),
                () -> verify(productService, atLeastOnce()).decreaseProductStock(any())
        );
    }
}
