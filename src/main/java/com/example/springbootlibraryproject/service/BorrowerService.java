package com.example.springbootlibraryproject.service;

import com.example.springbootlibraryproject.dto.request.BorrowerRequestDto;
import com.example.springbootlibraryproject.dto.response.BorrowerResponseDto;
import com.example.springbootlibraryproject.dto.updateRequest.BorrowerUpdateRequestDto;
import com.example.springbootlibraryproject.dto.updateRequest.StockUpdateRequestDto;
import com.example.springbootlibraryproject.entity.Borrower;
import com.example.springbootlibraryproject.entity.Stock;
import com.example.springbootlibraryproject.exceptions.BorrowerException;
import com.example.springbootlibraryproject.exceptions.StockException;
import com.example.springbootlibraryproject.mapper.BorrowerMapper;
import com.example.springbootlibraryproject.repository.BorrowerRepository;
import com.example.springbootlibraryproject.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BorrowerService {

    private final BorrowerRepository borrowerRepository;
    private final BorrowerMapper borrowerMapper;
    private final StockRepository stockRepository;
    private final StockService stockService;

    public List<BorrowerResponseDto> getBorrowersAll() {
        log.info("BorrowerService::getBorrowersAll started");

        List<Borrower> borrowerList = borrowerRepository.findAll();

        log.info("BorrowerService::getBorrowersAll finished");
        return borrowerMapper.mapToBorrowerResponseDtoList(borrowerList);
    }

    public BorrowerResponseDto createBorrower(BorrowerRequestDto borrowerRequestDto) {
        log.info("BorrowerService::createBorrower started");

        if (!(borrowerRequestDto.getAmountBorrowed() == 1)) {
            throw new BorrowerException("The borrowed amount must be a minimum and a maximum of 1");
        }

        Optional<Stock> optionalStock = getOptionalStock(borrowerRequestDto);
        if (optionalStock.get().getAmountOfStock() <= 0) {
            throw new BorrowerException("The minimum value of the borrowed amount cannot be less than 0.");
        }

        getUpdateStock(borrowerRequestDto, optionalStock);

        Borrower borrower = borrowerMapper.mapToBorrower(borrowerRequestDto);
        Borrower save = borrowerRepository.save(borrower);

        log.info("BorrowerService::createBorrower finished");
        return borrowerMapper.mapToBorrowerResponseDto(save);
    }

    public BorrowerResponseDto updateBorrower(BorrowerUpdateRequestDto borrowerUpdateRequestDto) {
        log.info("BorrowerService::updateBook started");

        Optional<Borrower> optionalBorrower = borrowerRepository.findById(borrowerUpdateRequestDto.getId());
        optionalBorrower.orElseThrow(() -> new BorrowerException("Borrower not found id : " + borrowerUpdateRequestDto.getId()));

        Borrower borrower = borrowerMapper.mapToBorrower(borrowerUpdateRequestDto);
        Borrower save = borrowerRepository.save(borrower);

        log.info("BookService::updateBook finished");
        return borrowerMapper.mapToBorrowerResponseDto(save);
    }

    public void deleteBook(long id) {
        log.info("BorrowerService::deleteBook started");

        Optional<Borrower> optionalBorrower = borrowerRepository.findById(id);
        optionalBorrower.orElseThrow(() -> new BorrowerException("Borrower not found id : " + id));

        log.info("BorrowerService::deleteBook finished");
        borrowerRepository.deleteById(id);
    }

    private Optional<Stock> getOptionalStock(BorrowerRequestDto borrowerRequestDto) {
        Optional<Stock> optionalStock = stockRepository.findById(borrowerRequestDto.getStockId());
        optionalStock.orElseThrow(() -> new StockException("Stock not found for id : " + borrowerRequestDto.getStockId()));
        return optionalStock;
    }

    private void getUpdateStock(BorrowerRequestDto borrowerRequestDto, Optional<Stock> optionalStock) {
        Long result = optionalStock.get().getAmountOfStock() - 1;
        stockService.updateStock(StockUpdateRequestDto.builder()
                .id(optionalStock.get().getId())
                .bookId(borrowerRequestDto.getBookId())
                .amountOfStock(result)
                .build());
    }
}
