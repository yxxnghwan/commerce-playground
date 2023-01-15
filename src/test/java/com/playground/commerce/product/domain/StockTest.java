package com.playground.commerce.product.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.playground.commerce.product.domain.exception.InvalidStockException;
import com.playground.commerce.product.domain.vo.Stock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StockTest {

    @Test
    @DisplayName("재고 객체를 생성한다.")
    void createStock() {
        final Stock stock = new Stock(1L);

        assertThat(stock.getValue()).isEqualTo(1L);
    }

    @Test
    @DisplayName("재고는 음수일 수 없다.")
    void createStockWithInvalidStock() {
        assertThatThrownBy(() -> new Stock(-1L))
                .isInstanceOf(InvalidStockException.class)
                .hasMessageContaining(String.valueOf(-1L));
    }

    @Test
    @DisplayName("재고를 감소한다.")
    void decrease() {
        final Stock stock = new Stock(1L);

        final Stock newStock = stock.decrease(1L);

        assertThat(newStock.getValue()).isEqualTo(0L);
    }

    @Test
    @DisplayName("현재 재고보다 많은 양을 감소할 수 없다.")
    void decreaseOverAmount() {
        final Stock stock = new Stock(1L);

        assertThatThrownBy(() -> stock.decrease(2L))
                .isInstanceOf(InvalidStockException.class)
                .hasMessageContaining(String.valueOf(-1L));
    }

    @Test
    @DisplayName("value 값이 같으면 같은 객체로 판단한다.")
    void testEquals() {
        final Stock stock1 = new Stock(1L);
        final Stock stock2 = new Stock(1L);

        assertThat(stock1.equals(stock2)).isTrue();
    }
}