package com.example.stockservice.controller;

import com.example.stockservice.dto.StockDTO;
import com.example.stockservice.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stockApi")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping("/getAll")
    public ResponseEntity<List<StockDTO>> getAllStock() {
        return ResponseEntity.ok(stockService.getAllStock());
    }

    @GetMapping("/getStock/id/{id}")
    public ResponseEntity<StockDTO> getStockById(@PathVariable String id){
        return ResponseEntity.ok(stockService.getStockById(id));
    }

    @GetMapping("/getStock/name/{name}")
    public ResponseEntity<StockDTO> getStockByName(@PathVariable String name) {
        return ResponseEntity.ok(stockService.getStockByName(name));
    }

    @PostMapping("/createStock")
    public ResponseEntity<StockDTO> createStock(@RequestBody StockDTO stockDto) {
        return ResponseEntity.ok(stockService.createStock(stockDto));
    }

    @PutMapping("/updateStock")
    public ResponseEntity<StockDTO> updateStock(@RequestBody StockDTO stockDto) {
        return ResponseEntity.ok(stockService.updateStock(stockDto));
    }

    @DeleteMapping("/deleteStock/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable String id) {
        return ResponseEntity.ok(stockService.deleteStock(id));
    }
}
