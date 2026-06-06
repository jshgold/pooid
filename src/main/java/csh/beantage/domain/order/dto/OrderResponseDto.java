package csh.beantage.domain.order.dto;

import csh.beantage.domain.order.entity.Order;
import csh.beantage.domain.orderItem.dto.OrderItemDto;
import csh.beantage.domain.orderItem.dto.OrderItemDto.*;

import java.util.List;

public record OrderResponseDto(
        Long id,
        String email,
        String address,
        List<OrderItemResponse> orderItems
) {
    public static OrderResponseDto from(Order order, List<OrderItemResponse> orderItems) {
        return new OrderResponseDto(
                order.getId(),
                order.getEmail(),
                order.getAddress(),
                orderItems
        );
    }
}
