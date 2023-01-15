package com.playground.commerce.order.domain;

import com.playground.commerce.order.domain.vo.ItemPricePerPiece;
import com.playground.commerce.order.domain.vo.Quantity;

public class OrderItem {

    private final String productNumber;
    private final String productName;
    private final ItemPricePerPiece itemPricePerPiece;
    private Quantity quantity;

    public OrderItem(final String productNumber,
                     final String productName,
                     final Long quantity,
                     final Long itemPricePerPiece) {
        this.productNumber = productNumber;
        this.productName = productName;
        this.quantity = new Quantity(quantity);
        this.itemPricePerPiece = new ItemPricePerPiece(itemPricePerPiece);
    }

    public void addQuantity(final long amount) {
        this.quantity = quantity.addQuantity(amount);
    }

    public boolean isSameProduct(final OrderItem target) {
        return this.productNumber.equals(target.getProductNumber());
    }

    public String getProductNumber() {
        return productNumber;
    }

    public Long getPrice() {
        return itemPricePerPiece.getValue() * quantity.getValue();
    }

    public String getProductName() {
        return productName;
    }

    public Long getItemPricePerPiece() {
        return itemPricePerPiece.getValue();
    }

    public Long getQuantity() {
        return quantity.getValue();
    }
}
