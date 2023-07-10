package com.example.coffeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CoffeeDTO {

    private long id;

    private String name;

    private Set<IngredientDTO> ingredients;
}
