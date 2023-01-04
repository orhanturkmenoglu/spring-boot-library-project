package com.example.springbootlibraryproject.mapper;

import com.example.springbootlibraryproject.dto.request.StockRequestDto;
import com.example.springbootlibraryproject.dto.response.StockResponseDto;
import com.example.springbootlibraryproject.dto.updateRequest.StockUpdateRequestDto;
import com.example.springbootlibraryproject.entity.Book;
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
                .book(Book.builder().id(stockUpdateRequestDto.getId()).build())
                .amountOfStock(stockUpdateRequestDto.getAmountOfStock())
                .build();
    }

    public Stock mapToStock(StockRequestDto stockRequestDto) {
        return Stock.builder()
                .book(Book.builder()
                        .name(stockRequestDto.getBook().getName())
                        .author(stockRequestDto.getBook().getAuthor())
                        .barcode(stockRequestDto.getBook().getBarcode())
                        .publisher(stockRequestDto.getBook().getPublisher())
                        .numbersOfPages(stockRequestDto.getBook().getNumbersOfPages())
                        .creationDate(stockRequestDto.getBook().getCreationDate())
                        .amountOfStock(stockRequestDto.getBook().getAmountOfStock())
                        .build())
                .amountOfStock(stockRequestDto.getAmountStock())
                .build();
    }

    public Stock mapToStockResponseDto(StockResponseDto stockResponseDto) {
        return Stock.builder()
                .book(stockResponseDto.getBook())
                .amountOfStock(stockResponseDto.getAmountOfStock())
                .build();
    }

    public StockResponseDto mapToStockResponseDto(Stock stock) {
        return StockResponseDto.builder()
                .book(stock.getBook())
                .amountOfStock(stock.getAmountOfStock())
                .build();
    }
}
