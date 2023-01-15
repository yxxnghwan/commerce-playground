package com.playground.commerce.order.application.dto;

public class OrderItemDto {

    private final String productNumber;
    private final String productName;
    private final Long itemPricePerPiece;
    private final Long quantity;

    public OrderItemDto(final String productNumber,
                        final String productName,
                        final Long quantity,
                        final Long itemPricePerPiece) {
        this.productNumber = productNumber;
        this.productName = productName;
        this.quantity = quantity;
        this.itemPricePerPiece = itemPricePerPiece;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public String getProductName() {
        return productName;
    }

    public Long getItemPricePerPiece() {
        return itemPricePerPiece;
    }

    public Long getQuantity() {
        return quantity;
    }
}
