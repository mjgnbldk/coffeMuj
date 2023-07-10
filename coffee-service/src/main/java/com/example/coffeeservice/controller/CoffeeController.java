package com.example.coffeeservice.controller;

import com.example.coffeeservice.dto.CoffeeDTO;
import com.example.coffeeservice.service.CoffeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/coffeeApi")
@RequiredArgsConstructor
public class CoffeeController {
    private final CoffeeService coffeeService;

    @GetMapping("/getAll")
    public ResponseEntity<List<CoffeeDTO>> getAllPizzas() {
        return ResponseEntity.ok(coffeeService.getAllCoffees());
    }

    @GetMapping("/getCoffee/id/{id}")
    public ResponseEntity<CoffeeDTO> getCoffeeById(@PathVariable long id) {
        return ResponseEntity.ok(coffeeService.getCoffeeById(id));
    }

    @GetMapping("/getCoffee/name/{name}")
    public ResponseEntity<CoffeeDTO> getCoffeeByName(@PathVariable String name) {
        return ResponseEntity.ok(coffeeService.getCoffeeByName(name));
    }

    @PostMapping("/createCoffee")
    public ResponseEntity<CoffeeDTO> createCoffee(@RequestBody CoffeeDTO coffeeDTO) {
        return ResponseEntity.ok(coffeeService.createCoffee(coffeeDTO));
    }

    @PutMapping("/updateCoffee")
    public ResponseEntity<CoffeeDTO> updateCoffee(@RequestBody CoffeeDTO coffeeDTO){
        return ResponseEntity.ok(coffeeService.updateCoffee(coffeeDTO));
    }

    @DeleteMapping("/deleteCoffee/{id}")
    public ResponseEntity<String> deleteCoffee(@PathVariable Long id){
        return ResponseEntity.ok(coffeeService.deleteCoffee(id));
    }
}
