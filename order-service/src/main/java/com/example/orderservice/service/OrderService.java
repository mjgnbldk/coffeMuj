package com.example.orderservice.service;

import com.example.orderservice.config.OrderMQConfig;
import com.example.orderservice.dto.OrderDTO;
import com.example.orderservice.mapper.OrderMapper;
import com.example.orderservice.repo.OrderRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepo orderRepo;

    private final OrderMapper orderMapper;

    private final RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper;

    public List<OrderDTO> getAll() {
        return orderRepo.findAll()
                .stream()
                .map(orderMapper::orderToOrderDto)
                .collect(Collectors.toList());

    }

    public List<OrderDTO> getOrdersByCoffeeName(String coffeeName) {
        return orderRepo.findOrdersByCoffeeName(coffeeName)
                .stream()
                .map(orderMapper::orderToOrderDto)
                .collect(Collectors.toList());
    }

    public List<OrderDTO> postOrders(List<String> coffees) {
        List<OrderDTO> orders = new ArrayList<>();
        coffees.forEach(coffee -> {
            OrderDTO orderDto = new OrderDTO();
            orderDto.setCoffeeName(coffee);
            orderDto.setOrderStatus("none");
            orders.add(orderDto);
        });
        orders.forEach(orderDto -> {
            try {
                String message = objectMapper.writeValueAsString(orderDto);
                rabbitTemplate.convertAndSend(OrderMQConfig.ORDER_EXCHANGE, OrderMQConfig.ORDER_ROUTING_KEY, message);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
        return orders;
    }
}
