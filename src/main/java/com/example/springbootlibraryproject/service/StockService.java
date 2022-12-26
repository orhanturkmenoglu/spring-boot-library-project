package com.example.springbootlibraryproject.service;

import com.example.springbootlibraryproject.dto.response.StockResponseDto;
import com.example.springbootlibraryproject.dto.updateRequest.StockUpdateRequestDto;
import com.example.springbootlibraryproject.entity.Stock;
import com.example.springbootlibraryproject.exceptions.StockException;
import com.example.springbootlibraryproject.mapper.StockMapper;
import com.example.springbootlibraryproject.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class StockService {

    private final StockRepository stockRepository;

    private final StockMapper stockMapper;


    public List<StockResponseDto> getStocksAll() {
        List<Stock> stockList = stockRepository.findAll();
        return stockMapper.mapToStockResponseDtoList(stockList);
    }

    public StockResponseDto updateStock(StockUpdateRequestDto stockUpdateRequestDto) {
        Optional<Stock> optionalStock = stockRepository.findById(stockUpdateRequestDto.getId());
        optionalStock.orElseThrow(() -> new StockException("Stock could not be found id :" + stockUpdateRequestDto.getId()));

        Stock stock = stockMapper.mapToStock(stockUpdateRequestDto);
        Stock save = stockRepository.save(stock);

        return stockMapper.mapToStockResponseDto(save);
    }

}
