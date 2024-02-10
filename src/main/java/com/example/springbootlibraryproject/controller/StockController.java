package com.example.springbootlibraryproject.controller;

import com.example.springbootlibraryproject.constants.StockMessage;
import com.example.springbootlibraryproject.dto.request.StockRequestDto;
import com.example.springbootlibraryproject.dto.response.StockResponseDto;
import com.example.springbootlibraryproject.exceptions.SuccessResponse;
import com.example.springbootlibraryproject.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stocks")
@RequiredArgsConstructor
@Slf4j
public class StockController {

    private final StockService stockService;

    @PostMapping("/createStock")
    public ResponseEntity<StockResponseDto> createStock(@RequestBody StockRequestDto stockRequestDto) {
        StockResponseDto stockResponseDto = stockService.createStock(stockRequestDto);
        return SuccessResponse.responseBuilder(StockMessage.CREATE_STOCK, HttpStatus.CREATED, stockResponseDto);
    }

    @GetMapping("/stocksAll")
    public ResponseEntity<List<StockResponseDto>> getStocksAll() {
        List<StockResponseDto> stockResponseDtoList = stockService.getStocksAll();
        return SuccessResponse.responseBuilder(StockMessage.GET_STOCKS_ALL, HttpStatus.OK, stockResponseDtoList);
    }
}
