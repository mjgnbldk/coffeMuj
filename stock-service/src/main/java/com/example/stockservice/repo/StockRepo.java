package com.example.stockservice.repo;

import com.example.stockservice.entity.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface StockRepo extends MongoRepository<Stock, String> {

    Stock findStockByName(String name);
}
