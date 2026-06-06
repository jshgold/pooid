package csh.beantage.domain.order.controller;

import csh.beantage.domain.order.dto.OrderRequestDto.CreateOrderRequest;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin/order")
@RestController
public class OrderApiAdminController {

    @GetMapping
    public void getOrder() {

    }
}
