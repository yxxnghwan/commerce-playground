package com.playground.commerce.product.application.dto;

public class ProductSellDto {

    private final String productNumber;
    private final Long quantity;

    public ProductSellDto(final String productNumber, final Long quantity) {
        this.productNumber = productNumber;
        this.quantity = quantity;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public Long getQuantity() {
        return quantity;
    }
}
