package dev.yonk.order.mappers;

import dev.yonk.order.dto.OrderDto;
import dev.yonk.order.models.Order;

public class OrderMapper {

    public static OrderDto MapToDto (Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .orderDate(order.getOrderDate())
                .shippingAddress(order.getShippingAddress())
                .status(order.getStatus())
                .build();
    }
}
