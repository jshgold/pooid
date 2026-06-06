package csh.beantage.domain.order.controller;

import csh.beantage.domain.order.dto.OrderRequestDto.CreateOrderRequest;
import csh.beantage.domain.order.dto.OrderResponseDto;
import csh.beantage.domain.order.service.OrderUserService;
import csh.beantage.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/order")
@RestController
public class OrderApiUserController {
    private final OrderUserService service;

    @PostMapping
    public ResponseEntity<ResponseDto<OrderResponseDto>> createOrder(@RequestBody CreateOrderRequest request) {
        log.info("create order request: {}", request);
        OrderResponseDto orderResponseDto = service.createOrder(request);
        return new ResponseEntity<>(
                new ResponseDto<>(
                        "201-1",
                        "와 성공했다",
                        orderResponseDto
                ), HttpStatus.CREATED
        );
    }
}
