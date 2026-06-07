package csh.beantage.domain.orderItem.entity;

import csh.beantage.domain.order.entity.Order;
import csh.beantage.domain.orderItem.dto.OrderItemDto.*;
import csh.beantage.domain.products.entity.Product;
import csh.beantage.global.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Entity
public class OrderItem extends BaseEntity {
    private Long productId;
    private Integer price;
    private Integer amount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id") // 실제 DB 테이블의 FK 컬럼명 명시
    private Order order;

    private OrderItem (Long productId, Integer amount) {
        this.productId = productId;
//        this.price = price;
        this.amount = amount;
    }

    // 1. Order 없이 생성하는 메서드
    public static OrderItem create(Long productId, Integer price, Integer amount) {
        OrderItem orderItem = new OrderItem();
        orderItem.productId = productId;
        orderItem.price = price;
        orderItem.amount = amount;
        return orderItem;
    }

    // 2. 나중에 부모(Order)가 자신을 리스트에 넣을 때, 이 메서드를 통해 양방향 세팅을 완료함
    public void assignOrder(Order order) {
        this.order = order;
    }
}
