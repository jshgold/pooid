package csh.beantage.domain.orderItem.entity;

import csh.beantage.domain.order.entity.Order;
import csh.beantage.domain.orderItem.dto.OrderItemDto.*;
import csh.beantage.domain.products.entity.Product;
import csh.beantage.global.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Entity
public class OrderItem extends BaseEntity {
    private Long productId;
    private Integer amount;

    private OrderItem (Long productId, Integer amount) {
        this.productId = productId;
        this.amount = amount;
    }

    public static OrderItem create(OrderItemRequest request) {
        return new OrderItem(request.productId(), request.amount());
    }
}
