package com.example.coffeeservice.listener;

import com.example.coffeeservice.config.CoffeeMQConfig;
import com.example.coffeeservice.config.OrderMQConfig;
import com.example.coffeeservice.config.OrderStatusMQConfig;
import com.example.coffeeservice.dto.CoffeeDTO;
import com.example.coffeeservice.dto.IngredientDTO;
import com.example.coffeeservice.dto.OrderDTO;
import com.example.coffeeservice.service.CoffeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderListener {

    private final CoffeeService coffeeService;
    private final RabbitTemplate rabbitTemplate;

    private final StockListener stockListener;

    private final ObjectMapper objectMapper;

    @RabbitListener(queues = OrderMQConfig.ORDER_QUEUE)
    public void listenOrder(String message) throws JsonProcessingException, InterruptedException {
        OrderDTO orderDto = convertMessageToOrder(message);
        CoffeeDTO coffeeDTO = coffeeService.getCoffeeByName(orderDto.getCoffeeName());
        if (coffeeDTO.getName() == null) {
            orderDto.setOrderStatus("Coffee Not Found");
        } else {
            List<String> stocks = coffeeDTO.getIngredients()
                    .stream()
                    .map(IngredientDTO::getName)
                    .collect(Collectors.toList());
            sendMessageToStock(convertJsonString(stocks));
            Thread.sleep(3000);
            orderDto.setOrderStatus(stockListener.getStockStatus());
        }
        sendResponseToOrder(convertJsonString(orderDto));
    }

    public void sendMessageToStock(String message) {
        rabbitTemplate.convertAndSend(CoffeeMQConfig.COFFEE_EXCHANGE, CoffeeMQConfig.COFFEE_ROUTING_KEY, message);
    }

    public void sendResponseToOrder(String response) {
        rabbitTemplate.convertAndSend(OrderStatusMQConfig.ORDER_STATUS_EXCHANGE, OrderStatusMQConfig.ORDER_STATUS_ROUTING_KEY, response);
    }

    private String convertJsonString(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    private OrderDTO convertMessageToOrder(String message) throws JsonProcessingException {
        return objectMapper.readValue(message, OrderDTO.class);
    }
}
