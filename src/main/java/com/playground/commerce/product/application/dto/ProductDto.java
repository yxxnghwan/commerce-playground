package com.playground.commerce.product.application.dto;

public class ProductDto {

    private final String productNumber;
    private final String name;
    private final Long productPrice;
    private final Long stock;

    public ProductDto(final String productNumber, final String name, final Long productPrice, final Long stock) {
        this.productNumber = productNumber;
        this.name = name;
        this.productPrice = productPrice;
        this.stock = stock;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public String getName() {
        return name;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public Long getStock() {
        return stock;
    }
}
