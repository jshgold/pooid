package csh.beantage.domain.order.service;

import csh.beantage.domain.order.dto.OrderRequestDto.CreateOrderRequest;
import csh.beantage.domain.order.dto.OrderResponseDto;
import csh.beantage.domain.order.entity.Order;
import csh.beantage.domain.order.repository.OrderRepository;
import csh.beantage.domain.orderItem.dto.OrderItemDto.OrderItemResponse;
import csh.beantage.domain.orderItem.entity.OrderItem;
import csh.beantage.domain.products.entity.Product;
import csh.beantage.domain.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class OrderUserService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderResponseDto createOrder(CreateOrderRequest request) { // 이건 오더의 DTO야 여기안에는 오더의 관련된 필드 + 주문상품리스트
        List<Long> productIds = request.orderItems().stream().map(orderItem -> orderItem.productId()).toList();
        Map<Long, Product> productMap = productRepository.findAllById(productIds).stream()
                .collect(Collectors.toMap(Product::getId, p -> p));

        // 1. DTO(Request)를 순수한 OrderItem 엔티티 리스트로 변환 (아직 Order는 모름)
        List<OrderItem> orderItems = request.orderItems().stream()
                .map(itemDto -> OrderItem.create(
                        itemDto.productId(),
                        productMap.get(itemDto.productId()).getPrice(),
                        itemDto.amount()
                ))
                .toList();


        // 2. Order 엔티티 생성 (이 안에서 양방향 연관관계 매핑 및 totalPrice 계산이 모두 끝남)
        Order order = Order.create(
                request.email(),
                request.address(),
                orderItems
        );

        orderRepository.save(order);

        List<OrderItemResponse> orderItemResponses = order.getOrderItems().stream().map(orderItem -> {
            Product product = productMap.get(orderItem.getProductId());
            return OrderItemResponse.from(orderItem, product.getName(), product.getPrice());
        }).toList();

        return OrderResponseDto.from(order, orderItemResponses);
    }
}
