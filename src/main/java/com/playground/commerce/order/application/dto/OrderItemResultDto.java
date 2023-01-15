package com.playground.commerce.order.application.dto;

public class OrderItemResultDto {

    private final String productName;
    private final Long quantity;

    public OrderItemResultDto(final String productName, final Long quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public Long getQuantity() {
        return quantity;
    }
}
