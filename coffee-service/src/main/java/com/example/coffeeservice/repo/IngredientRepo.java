package com.example.coffeeservice.repo;

import com.example.coffeeservice.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepo  extends JpaRepository<Ingredient, Long> {

    Ingredient findIngredientByName(String name);
}
