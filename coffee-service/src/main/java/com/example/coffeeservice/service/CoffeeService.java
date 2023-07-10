package com.example.coffeeservice.service;

import com.example.coffeeservice.dto.CoffeeDTO;
import com.example.coffeeservice.entity.Coffee;
import com.example.coffeeservice.mapper.CoffeeMapper;
import com.example.coffeeservice.repo.CoffeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CoffeeService {

    private final CoffeeRepo coffeeRepo;

    private final CoffeeMapper coffeeMapper;

    public List<CoffeeDTO> getAllCoffees() {
        return coffeeRepo.findAll()
                .stream()
                .map(coffeeMapper::coffeeToCoffeeDto)
                .collect(Collectors.toList());
    }

    public CoffeeDTO getCoffeeById(long id) {
        return coffeeMapper.coffeeToCoffeeDto(coffeeRepo.findById(id).orElse(null));
    }

    public CoffeeDTO getCoffeeByName(String name) {
        CoffeeDTO coffeeDto = coffeeMapper.coffeeToCoffeeDto(coffeeRepo.findCoffeeByName(name));
        if(coffeeDto == null){
            return new CoffeeDTO();
        }
        return coffeeDto;
    }

    public CoffeeDTO createCoffee(CoffeeDTO coffeeDTO) {
        Coffee coffee = coffeeRepo.save(coffeeMapper.coffeeDtoToCoffee(coffeeDTO));
        return coffeeMapper.coffeeToCoffeeDto(coffee);
    }

    public CoffeeDTO updateCoffee(CoffeeDTO coffeeDTO) {
        Coffee updatedCoffee = coffeeMapper.coffeeDtoToCoffee(coffeeDTO);
        Coffee coffee = coffeeRepo.findById(coffeeDTO.getId()).orElse(null);
        coffee.setName(updatedCoffee.getName());
        coffee.setIngredients(updatedCoffee.getIngredients());
        coffeeRepo.save(coffee);
        return coffeeMapper.coffeeToCoffeeDto(coffee);
    }

    public String deleteCoffee(Long id) {
        coffeeRepo.findById(id).ifPresent(coffeeRepo::delete);
        return "Coffee is deleted";
    }

}
