package com.example.springbootlibraryproject.controller;

import com.example.springbootlibraryproject.dto.response.StockResponseDto;
import com.example.springbootlibraryproject.exceptions.SuccessResponse;
import com.example.springbootlibraryproject.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stocks")
@RequiredArgsConstructor
@Slf4j
public class StockController {

    private final StockService stockService;

    @GetMapping
    public ResponseEntity<List<StockResponseDto>> getStocksAll() {
        List<StockResponseDto> stockResponseDtoList = stockService.getStocksAll();
        return SuccessResponse.responseBuilder("Fetch stock list successful", HttpStatus.OK, stockResponseDtoList);
    }
}
