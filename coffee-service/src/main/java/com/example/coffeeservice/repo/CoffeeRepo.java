package com.example.coffeeservice.repo;

import com.example.coffeeservice.entity.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepo extends JpaRepository<Coffee, Long> {

    Coffee findCoffeeByName(String name);
}
