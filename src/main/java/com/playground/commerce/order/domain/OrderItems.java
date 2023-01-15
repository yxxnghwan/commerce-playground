package com.playground.commerce.order.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderItems {

    private final List<OrderItem> orderItems;

    public OrderItems(final List<OrderItem> orderItems) {
        this.orderItems = mergeOrderItems(orderItems);
    }

    private List<OrderItem> mergeOrderItems(final List<OrderItem> orderItems) {
        final List<OrderItem> mergedOrderItems = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            mergedOrderItems.stream()
                    .filter(it -> it.isSameProduct(orderItem))
                    .findAny()
                    .ifPresentOrElse(it -> it.addQuantity(orderItem.getQuantity()),
                            () -> mergedOrderItems.add(orderItem)
                    );
        }
        return mergedOrderItems;
    }

    public Long getTotalPrice() {
        return orderItems.stream()
                .mapToLong(OrderItem::getPrice)
                .sum();
    }

    public List<OrderItem> getValues() {
        return Collections.unmodifiableList(orderItems);
    }
}
