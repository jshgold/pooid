package csh.beantage.domain.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import csh.beantage.domain.orderItem.dto.OrderItemDto.OrderItemRequest;

import java.util.List;

public class OrderRequestDto {
    public record CreateOrderRequest(
            String email,
            String address,
            @JsonProperty("orderItems") List<OrderItemRequest> orderItems
    ) {}
}
