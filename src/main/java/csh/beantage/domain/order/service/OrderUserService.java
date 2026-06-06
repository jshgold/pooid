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

        List<Integer> priceList = request.orderItems().stream()
                .map(
                        item -> item.amount() * productMap.get(item.productId()).getPrice()
                )
                .toList();
        Integer totalPrice = priceList.stream().mapToInt(Integer::valueOf).sum();

        Order order = Order.create(
                request.email(),
                request.address(),
                totalPrice,
                request.orderItems().stream().map(OrderItem::create).toList()
        );

        orderRepository.save(order);

        List<OrderItemResponse> orderItemResponses = order.getOrderItems().stream().map(orderItem -> {
            Product product = productMap.get(orderItem.getProductId());
            return OrderItemResponse.from(orderItem, product.getName(), product.getPrice());
        }).toList();

        return OrderResponseDto.from(order, orderItemResponses);
    }
}
