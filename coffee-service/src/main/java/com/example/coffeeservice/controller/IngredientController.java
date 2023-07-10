package com.example.coffeeservice.controller;

import com.example.coffeeservice.dto.IngredientDTO;
import com.example.coffeeservice.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/ingredientApi")
@RequiredArgsConstructor
public class IngredientController {
    private final IngredientService ingredientService;

    @GetMapping("/getAll")
    public ResponseEntity<List<IngredientDTO>> getAllIngredients() {
        return ResponseEntity.ok(ingredientService.getAllIngredients());
    }

    @GetMapping("/getIngredient/id/{id}")
    public ResponseEntity<IngredientDTO> getIngredientById(@PathVariable long id) {
        return ResponseEntity.ok(ingredientService.getIngredientById(id));
    }

    @GetMapping("/getIngredient/name/{name}")
    public ResponseEntity<IngredientDTO> getIngredientByName(@PathVariable String name) {
        return ResponseEntity.ok(ingredientService.getIngredientByName(name));
    }

    @PostMapping("/createIngredient")
    public ResponseEntity<IngredientDTO> createIngredient(@RequestBody IngredientDTO ingredientDto) {
        return ResponseEntity.ok(ingredientService.createIngredient(ingredientDto));
    }

    @PutMapping("/updateIngredient")
    public ResponseEntity<IngredientDTO> updateIngredient(@RequestBody IngredientDTO ingredientDto){
        return ResponseEntity.ok(ingredientService.updateIngredient(ingredientDto));
    }

    @DeleteMapping("/deleteIngredient/{id}")
    public ResponseEntity<String> deleteIngredient(@PathVariable Long id){
        return ResponseEntity.ok(ingredientService.deleteIngredient(id));
    }
}
