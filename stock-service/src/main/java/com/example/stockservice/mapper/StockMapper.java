package com.example.stockservice.mapper;

import com.example.stockservice.dto.StockDTO;
import com.example.stockservice.entity.Stock;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMapper {
    Stock stockDtoToStock(StockDTO stockDto);

    StockDTO stockToStockDto(Stock stock);
}
