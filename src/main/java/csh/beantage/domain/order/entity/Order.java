package csh.beantage.domain.order.entity;

import csh.beantage.domain.order.dto.OrderRequestDto.*;
import csh.beantage.domain.orderItem.entity.OrderItem;
import csh.beantage.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
@Entity
public class Order extends BaseEntity {
    private String email;
    private String address;
    private Integer totalPrice;
    @OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderItem> orderItems;

    public static Order create(String email, String address, Integer totalPrice, List<OrderItem> orderItems) {
        Order order = new Order(
                email,
                address,
                totalPrice,
                orderItems
        );
        return order;
    }

    private Order(
            String email,
            String address,
            Integer totalPrice,
            List<OrderItem> orderItems
    ) {
        this.email = email;
        this.address = address;
        this.totalPrice = totalPrice;
        this.orderItems = orderItems;
    }
}
