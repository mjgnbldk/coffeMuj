package com.example.coffeeservice.mapper;

import com.example.coffeeservice.dto.IngredientDTO;
import com.example.coffeeservice.entity.Ingredient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IngredientMapper {

    Ingredient ingredientDtoToIngredient(IngredientDTO ingredientDto);

    IngredientDTO ingredientToIngredientDto(Ingredient ingredient);
}
