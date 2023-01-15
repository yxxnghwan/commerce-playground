package com.playground.commerce.order.domain;

public interface DeliveryFeeCalculator {

    long calculateDeliveryFee(final long totalPrice);
}
