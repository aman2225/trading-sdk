
package com.bajaj.trading_sdk.controller;

import com.bajaj.trading_sdk.model.OrderEntity;
import com.bajaj.trading_sdk.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService service;

    @PostMapping
    public OrderEntity place(@RequestBody OrderEntity o) {
        log.info("API POST /orders");
        return service.placeOrder(o);
    }

    @GetMapping("/{id}")
    public OrderEntity get(@PathVariable Long id) {
        log.info("API GET /orders/{}", id);
        return service.getOrder(id);
    }

    @PutMapping("/{id}/cancel")
    public OrderEntity cancel(@PathVariable Long id) {
        log.info("API PUT /orders/{}/cancel", id);
        return service.cancelOrder(id);
    }
}

