package com.playground.commerce.order.application;

import com.playground.commerce.order.application.dto.OrderItemDto;
import com.playground.commerce.order.application.dto.OrderItemResultDto;
import com.playground.commerce.order.application.dto.OrderResultDto;
import com.playground.commerce.order.domain.DeliveryFeeCalculator;
import com.playground.commerce.order.domain.OrderItem;
import com.playground.commerce.order.domain.OrderItems;
import com.playground.commerce.product.application.ProductService;
import com.playground.commerce.product.application.dto.ProductSellDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final DeliveryFeeCalculator deliveryFeeCalculator;
    private final ProductService productService;

    public OrderService(final DeliveryFeeCalculator deliveryFeeCalculator,
                        final ProductService productService) {
        this.deliveryFeeCalculator = deliveryFeeCalculator;
        this.productService = productService;
    }

    @Transactional
    public OrderResultDto order(final List<OrderItemDto> orderItemDtos) {
        final OrderItems orderItems = new OrderItems(mapToOrderItems(orderItemDtos));
        publishOrderEvent(orderItems);
        final Long orderItemsTotalPrice = orderItems.getTotalPrice();
        return new OrderResultDto(
                mapToOrderItemResultDtos(orderItems),
                orderItemsTotalPrice,
                orderItemsTotalPrice + deliveryFeeCalculator.calculateDeliveryFee(orderItemsTotalPrice)
        );
    }

    private List<OrderItem> mapToOrderItems(final List<OrderItemDto> orderItemDtos) {
        return orderItemDtos.stream()
                .map(this::mapToOrderItem)
                .collect(Collectors.toList());
    }

    private OrderItem mapToOrderItem(final OrderItemDto orderItemDto) {
        return new OrderItem(
                orderItemDto.getProductNumber(),
                orderItemDto.getProductName(),
                orderItemDto.getQuantity(),
                orderItemDto.getItemPricePerPiece()
        );
    }

    private void publishOrderEvent(final OrderItems orderItems) {
        final List<ProductSellDto> productSellDtos = orderItems.getValues()
                .stream()
                .map(it -> new ProductSellDto(it.getProductNumber(), it.getQuantity()))
                .toList();

        productService.decreaseProductStock(productSellDtos);
    }

    private List<OrderItemResultDto> mapToOrderItemResultDtos(final OrderItems orderItems) {
        return orderItems.getValues()
                .stream()
                .map(it -> new OrderItemResultDto(it.getProductName(), it.getQuantity()))
                .collect(Collectors.toList());
    }
}
