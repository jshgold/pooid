package csh.beantage.domain.orderItem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import csh.beantage.domain.orderItem.entity.OrderItem;

public class OrderItemDto {
    public record OrderItemRequest(
            @JsonProperty("productId") Long productId,
            @JsonProperty("amount") Integer amount
    ){}

    public record OrderItemResponse(
            String name,
            Integer amount,
            Integer price
    ){
        public static OrderItemResponse from(OrderItem orderItem, String productName, Integer price) {
            return new OrderItemResponse(
                    productName,
                    orderItem.getAmount(),
                    price
            );
        }
    }
}
