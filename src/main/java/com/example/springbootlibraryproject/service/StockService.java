package com.example.springbootlibraryproject.service;

import com.example.springbootlibraryproject.dto.request.StockRequestDto;
import com.example.springbootlibraryproject.dto.response.BookResponseDto;
import com.example.springbootlibraryproject.dto.response.StockResponseDto;
import com.example.springbootlibraryproject.dto.updateRequest.StockUpdateRequestDto;
import com.example.springbootlibraryproject.entity.Stock;
import com.example.springbootlibraryproject.exceptions.StockException;
import com.example.springbootlibraryproject.mapper.StockMapper;
import com.example.springbootlibraryproject.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class StockService {

    private final StockRepository stockRepository;
    private final StockMapper stockMapper;
    private final BookService bookService;

    public StockResponseDto createStock(StockRequestDto stockRequestDto) {
        log.info("StockService::createStock started");

        String bookBarcode = stockRequestDto.getBook().getBarcode();
        List<BookResponseDto> bookResponseDtoList = bookService.getBooksAll();

        for (BookResponseDto bookResponseDto : bookResponseDtoList) {
            if (bookBarcode.equalsIgnoreCase(bookResponseDto.getBarcode())) {
                throw new StockException("Barcode previously defined!");
            }
        }

        Stock save = getStockSave(stockMapper.mapToStock(stockRequestDto));

        log.info("StockService::createStock finished");
        return stockMapper.mapToStockResponseDto(save);
    }

    @Cacheable(value = "stock")
    public List<StockResponseDto> getStocksAll() {
        log.info("StockService::getStocksAll started");

        List<Stock> stockList = stockRepository.findAll();

        log.info("StockService::getStocksAll finished");
        return stockMapper.mapToStockResponseDtoList(stockList);
    }

    @CachePut(value = "stock",key = "#stockUpdateRequestDto")
    public StockResponseDto updateStock(StockUpdateRequestDto stockUpdateRequestDto) {
        log.info("StockService::updateStock started");

        Optional<Stock> optionalStock = stockRepository.findById(stockUpdateRequestDto.getId());
        optionalStock.orElseThrow(() -> new StockException("Stock could not be found id :" + stockUpdateRequestDto.getId()));

        Stock save = getStockSave(stockMapper.mapToStock(stockUpdateRequestDto));

        log.info("StockService::updateStock finished");
        return stockMapper.mapToStockResponseDto(save);
    }

    private Stock getStockSave(Stock stockMapper) {
        Stock stock = stockMapper;
        Stock save = stockRepository.save(stock);
        return save;
    }
}
