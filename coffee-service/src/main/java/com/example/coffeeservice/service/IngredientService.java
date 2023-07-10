package com.example.coffeeservice.service;

import com.example.coffeeservice.dto.IngredientDTO;
import com.example.coffeeservice.entity.Ingredient;
import com.example.coffeeservice.mapper.IngredientMapper;
import com.example.coffeeservice.repo.IngredientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepo ingredientRepo;

    private final IngredientMapper ingredientMapper;


    public List<IngredientDTO> getAllIngredients() {
        return ingredientRepo.findAll()
                .stream()
                .map(ingredientMapper::ingredientToIngredientDto)
                .collect(Collectors.toList());
    }

    public IngredientDTO getIngredientById(long id) {
        return ingredientMapper.ingredientToIngredientDto(ingredientRepo.findById(id).orElse(null));
    }

    public IngredientDTO getIngredientByName(String name) {
        return ingredientMapper.ingredientToIngredientDto(ingredientRepo.findIngredientByName(name));
    }

    public IngredientDTO createIngredient(IngredientDTO ingredientDto) {
        Ingredient ingredient = ingredientRepo.save(ingredientMapper.ingredientDtoToIngredient(ingredientDto));
        return ingredientMapper.ingredientToIngredientDto(ingredient);
    }

    public IngredientDTO updateIngredient(IngredientDTO ingredientDto) {
        Ingredient ingredient = ingredientRepo.findById(ingredientDto.getId()).orElse(null);
        ingredient.setName(ingredientDto.getName());
        ingredientRepo.save(ingredient);
        return ingredientMapper.ingredientToIngredientDto(ingredient);
    }

    public String deleteIngredient(Long id) {
        ingredientRepo.findById(id).ifPresent(ingredientRepo::delete);
        return "Ingredient is deleted";
    }
}
