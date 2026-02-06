package com.viscovich.order_service.orders.controller;

import com.viscovich.order_service.orders.model.Order;
import com.viscovich.order_service.orders.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService service;

    OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public List<Order> allOrders() {
        return service.getAllOrders();
    }

    @GetMapping("/customer/{customerId}")
    public List<Order> getByCustomer(@PathVariable Long customerId){
        return service.getOrdersByCustomer(customerId);
    }

    @PostMapping("/customer/{customerId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@PathVariable Long customerId,
                             @RequestParam List<Long> productIds,
                             @RequestBody Order order) {
        return service.createOrder(customerId, productIds, order);
    }
}
