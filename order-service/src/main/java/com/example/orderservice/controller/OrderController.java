package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderDTO;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController  {

    private final OrderService orderService;

    @GetMapping("/all")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAll());
    }


    @GetMapping("/orders/name/{coffeeName}")
    public ResponseEntity<List<OrderDTO>> getOrdersByCoffeeName(@PathVariable String coffeeName) {
        return ResponseEntity.ok(orderService.getOrdersByCoffeeName(coffeeName));
    }

    @PostMapping("/orders")
    public ResponseEntity<List<OrderDTO>> postOrders(@RequestBody List<String> coffees) {
        return ResponseEntity.ok(orderService.postOrders(coffees));
    }
}
