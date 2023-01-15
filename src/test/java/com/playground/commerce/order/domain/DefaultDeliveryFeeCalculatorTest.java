package com.playground.commerce.order.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DefaultDeliveryFeeCalculatorTest {

    @ParameterizedTest
    @CsvSource(value = {"50000:0", "50001:0", "49999:2500"}, delimiter = ':')
    @DisplayName("50000원 이상이면 배송비가 0원, 아니면 2500원으로 배송비를 계산한다.")
    void calculateDeliveryFee(final Long totalPrice, final Long expectedDeliveryFee) {
        final DefaultDeliveryFeeCalculator defaultDeliveryFeeCalculator = new DefaultDeliveryFeeCalculator();

        assertThat(defaultDeliveryFeeCalculator.calculateDeliveryFee(totalPrice)).isEqualTo(expectedDeliveryFee);
    }
}
