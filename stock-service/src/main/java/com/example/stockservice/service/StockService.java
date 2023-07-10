package com.example.stockservice.service;

import com.example.stockservice.dto.StockDTO;
import com.example.stockservice.entity.Stock;
import com.example.stockservice.mapper.StockMapper;
import com.example.stockservice.repo.StockRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepo stockRepo;

    private final StockMapper stockMapper;

    public List<StockDTO> getAllStock() {
        return stockRepo.findAll()
                .stream()
                .map(stockMapper::stockToStockDto)
                .collect(Collectors.toList());
    }

    public StockDTO getStockById(String id) {
        return stockMapper.stockToStockDto(stockRepo.findById(id).orElse(null));
    }

    public StockDTO getStockByName(String name) {
        Stock stock = stockRepo.findStockByName(name);
        if(stock == null){
            return new StockDTO();
        }
        return stockMapper.stockToStockDto(stock);
    }

    public StockDTO createStock(StockDTO stockDto) {
        Stock stock = stockMapper.stockDtoToStock(stockDto);
        stockRepo.save(stock);
        return stockMapper.stockToStockDto(stock);
    }

    public StockDTO updateStock(StockDTO stockDto) {
        Stock stock = stockRepo.findById(stockDto.getId()).orElse(null);
        stock.setName(stockDto.getName());
        stock.setAmount(stockDto.getAmount());
        stockRepo.save(stock);
        return stockMapper.stockToStockDto(stock);
    }

    public String deleteStock(String id){
        Optional<Stock> stock = stockRepo.findById(id);
        if(stock.isPresent()) {
            stockRepo.deleteById(id);
            return "Stock is deleted";
        }
        return "Wrong id";
    }

    public StockDTO decrementAmount(String name){
        StockDTO stockDTO = getStockByName(name);
        if(stockDTO.getName() != null && stockDTO.getAmount() > 0) {
            stockDTO.setAmount(stockDTO.getAmount() - 1);
            stockRepo.save(stockMapper.stockDtoToStock(stockDTO));
        }
        return stockDTO;
    }
}
