package com.example.orderservice.repo;

import com.example.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Integer> {

        List<Order> findOrdersByCoffeeName(String coffeeName);
}
