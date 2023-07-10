package com.example.stockservice.listener;

import com.example.stockservice.config.CoffeeMQConfig;
import com.example.stockservice.dto.StockDTO;
import com.example.stockservice.service.StockService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CoffeeListener {

    private final RabbitTemplate rabbitTemplate;

    private final StockService stockService;

    private final ObjectMapper objectMapper;

    private String coffeeName;

    @RabbitListener(queues = CoffeeMQConfig.COFFEE_QUEUE)
    public void listenCoffee(String message) throws JsonProcessingException{
        //milk-coffee-sugar
        List<String> ingredients = objectMapper.readValue(message, List.class);
        for(String ingredient : ingredients) {
            StockDTO stockDTO = stockService.decrementAmount(ingredient);
            if(stockDTO.getName() == null){
                sendMessageToCoffee("STOCK NOT FOUND!");
                break;
            }
            if(stockDTO.getAmount() == 0){
                sendMessageToCoffee("NOT ENOUGH STOCK");
                break;
            }
        }
        sendMessageToCoffee("ok");

    }
    public String getCoffeeName() {
        return coffeeName;
    }

    private void sendMessageToCoffee(String response){
        rabbitTemplate.convertAndSend(CoffeeMQConfig.COFFEE_EXCHANGE, CoffeeMQConfig.STOCK_ROUTING_KEY, response);
    }
}
