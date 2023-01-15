package com.playground.commerce.order.application.dto;

import java.util.List;

public class OrderResultDto {

    private final List<OrderItemResultDto> orderItemResultDtos;
    private final Long orderAmount;
    private final Long paymentAmount;

    public OrderResultDto(final List<OrderItemResultDto> orderItemResultDtos,
                          final Long orderAmount,
                          final Long paymentAmount) {
        this.orderItemResultDtos = orderItemResultDtos;
        this.orderAmount = orderAmount;
        this.paymentAmount = paymentAmount;
    }

    public List<OrderItemResultDto> getOrderItemResultDtos() {
        return orderItemResultDtos;
    }

    public Long getOrderAmount() {
        return orderAmount;
    }

    public Long getPaymentAmount() {
        return paymentAmount;
    }
}
