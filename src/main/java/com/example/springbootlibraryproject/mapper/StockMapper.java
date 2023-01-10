package com.example.springbootlibraryproject.mapper;

import com.example.springbootlibraryproject.dto.request.StockRequestDto;
import com.example.springbootlibraryproject.dto.response.StockResponseDto;
import com.example.springbootlibraryproject.dto.updateRequest.StockUpdateRequestDto;
import com.example.springbootlibraryproject.entity.Stock;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StockMapper {

    public List<StockResponseDto> mapToStockResponseDtoList(List<Stock> stockList) {
        return stockList
                .stream()
                .map(this::mapToStockResponseDto)
                .collect(Collectors.toList());
    }

    public Stock mapToStock(StockUpdateRequestDto stockUpdateRequestDto) {
        return Stock.builder()
                .id(stockUpdateRequestDto.getId())
                .amountOfStock(stockUpdateRequestDto.getAmountOfStock())
                .build();
    }

    public Stock mapToStock(StockRequestDto stockRequestDto) {
        return Stock.builder()
                .amountOfStock(stockRequestDto.getBook().getAmountOfStock())
                .build();
    }

    public Stock mapToStockResponseDto(StockResponseDto stockResponseDto) {
        return Stock.builder()
                .amountOfStock(stockResponseDto.getAmountOfStock())
                .build();
    }

    public StockResponseDto mapToStockResponseDto(Stock stock) {
        return StockResponseDto.builder()
                .amountOfStock(stock.getAmountOfStock())
                .build();
    }
}
