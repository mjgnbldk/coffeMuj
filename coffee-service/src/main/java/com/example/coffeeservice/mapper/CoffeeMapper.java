package com.example.coffeeservice.mapper;

import com.example.coffeeservice.dto.CoffeeDTO;
import com.example.coffeeservice.entity.Coffee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoffeeMapper {

    Coffee coffeeDtoToCoffee(CoffeeDTO coffeeDTO);

    CoffeeDTO coffeeToCoffeeDto(Coffee coffee);
}
