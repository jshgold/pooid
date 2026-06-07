package csh.beantage.domain.order.entity;

import csh.beantage.domain.order.dto.OrderRequestDto.*;
import csh.beantage.domain.orderItem.entity.OrderItem;
import csh.beantage.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
@Entity
public class Order extends BaseEntity {
    private String email;
    private String address;
    private Integer totalPrice;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    // 1. 연관관계 편의 메서드 (핵심)
    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.assignOrder(this); // 자식 객체에 부모를 세팅해줌 (양방향 동기화)
    }

    // 2. Order 생성 팩토리 메서드
    public static Order create(String email, String address, List<OrderItem> items) {
        Order order = new Order();
        order.email = email;
        order.address = address;
        order.totalPrice = 0;

        for (OrderItem item : items) {
            order.addOrderItem(item); // 리스트에 넣으면서 양방향 연관관계 맺기
            order.totalPrice += (item.getPrice() * item.getAmount()); // 총액 자동 계산
        }
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
