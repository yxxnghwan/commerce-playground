package com.playground.commerce.order.domain;

import org.springframework.stereotype.Component;

@Component
public class DefaultDeliveryFeeCalculator implements DeliveryFeeCalculator {

    private static final int DELIVERY_FEE = 2500;
    private static final int DELIVERY_DISCOUNT_STANDARD = 50000;

    @Override
    public long calculateDeliveryFee(final long totalPrice) {
        if (totalPrice >= DELIVERY_DISCOUNT_STANDARD) {
            return 0;
        }
        return DELIVERY_FEE;
    }
}
