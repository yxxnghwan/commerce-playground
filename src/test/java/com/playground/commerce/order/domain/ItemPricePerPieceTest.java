package com.playground.commerce.order.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.playground.commerce.order.domain.exception.InvalidPricePerPieceException;
import com.playground.commerce.order.domain.vo.ItemPricePerPiece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ItemPricePerPieceTest {

    @Test
    @DisplayName("상품당 가격 객체를 생성한다.")
    void createItemPricePerPiece() {
        final ItemPricePerPiece itemPricePerPiece = new ItemPricePerPiece(1L);

        assertThat(itemPricePerPiece.getValue()).isEqualTo(1L);
    }

    @ParameterizedTest
    @ValueSource(longs = {0L, -1L})
    @DisplayName("0 이하의 상품당 가격은 존재할 수 없다.")
    void createItemPricePerPieceWithInvalidPrice(final long invalidPrice) {
        assertThatThrownBy(() -> new ItemPricePerPiece(invalidPrice))
                .isInstanceOf(InvalidPricePerPieceException.class)
                .hasMessageContaining(String.valueOf(invalidPrice));
    }

    @Test
    @DisplayName("value 값이 같으면 같은 객체로 판단한다.")
    void testEquals() {
        final ItemPricePerPiece itemPricePerPiece1 = new ItemPricePerPiece(1L);
        final ItemPricePerPiece itemPricePerPiece2 = new ItemPricePerPiece(1L);

        assertThat(itemPricePerPiece1.equals(itemPricePerPiece2)).isTrue();
    }
}
