package com.example.springbootlibraryproject.service;

import com.example.springbootlibraryproject.dto.request.BorrowerRequestDto;
import com.example.springbootlibraryproject.dto.response.BorrowerResponseDto;
import com.example.springbootlibraryproject.dto.updateRequest.BorrowerUpdateRequestDto;
import com.example.springbootlibraryproject.dto.updateRequest.StockUpdateRequestDto;
import com.example.springbootlibraryproject.entity.Book;
import com.example.springbootlibraryproject.entity.Borrower;
import com.example.springbootlibraryproject.entity.Member;
import com.example.springbootlibraryproject.entity.Stock;
import com.example.springbootlibraryproject.exceptions.BookException;
import com.example.springbootlibraryproject.exceptions.BorrowerException;
import com.example.springbootlibraryproject.exceptions.MemberException;
import com.example.springbootlibraryproject.exceptions.StockException;
import com.example.springbootlibraryproject.mapper.BorrowerMapper;
import com.example.springbootlibraryproject.repository.BookRepository;
import com.example.springbootlibraryproject.repository.BorrowerRepository;
import com.example.springbootlibraryproject.repository.MemberRepository;
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
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    public List<BorrowerResponseDto> getBorrowersAll() {
        log.info("BorrowerService::getBorrowersAll started");

        List<Borrower> borrowerList = borrowerRepository.findAll();

        log.info("BorrowerService::getBorrowersAll finished.");
        return borrowerMapper.mapToBorrowerResponseDtoList(borrowerList);
    }

    public BorrowerResponseDto createBorrower(BorrowerRequestDto borrowerRequestDto) {
        log.info("BorrowerService::createBorrower started");

        checkOptionalMemberById(borrowerRequestDto.getMemberId());

        checkOptionalBookById(borrowerRequestDto.getBookId());

        Optional<Stock> optionalStock = checkOptionalStockId(borrowerRequestDto.getStockId());

        if (!borrowerRequestDto.isStatus()) {
            throw new BorrowerException("The state cannot be set to false while borrowing.");
        }

        if (!(borrowerRequestDto.getAmountBorrowed() == 1)) {
            throw new BorrowerException("The borrowed amount must be a minimum and a maximum of 1.");
        }

        if (optionalStock.get().getAmountOfStock() <= 0) {
            throw new BorrowerException("The minimum value of the borrowed amount cannot be less than 0.");
        }

        Borrower save = getBorrowerSave(borrowerMapper.mapToBorrower(borrowerRequestDto));

        Long amountOfStock = optionalStock.get().getAmountOfStock() - 1;
        getUpdateStock(borrowerRequestDto.getBookId(), borrowerRequestDto.getStockId(), amountOfStock);


        log.info("BorrowerService::createBorrower finished.");
        return borrowerMapper.mapToBorrowerResponseDto(save);
    }


    public BorrowerResponseDto updateBorrower(BorrowerUpdateRequestDto borrowerUpdateRequestDto) {
        log.info("BorrowerService::updateBook started.");

        checkOptionalBorrowerById(borrowerUpdateRequestDto.getId());

        checkOptionalMemberById(borrowerUpdateRequestDto.getMemberId());

        checkOptionalBookById(borrowerUpdateRequestDto.getBookId());

        Optional<Stock> optionalStock = checkOptionalStockId(borrowerUpdateRequestDto.getStockId());

        if (!(borrowerUpdateRequestDto.getAmountBorrowed() == 1)) {
            throw new BorrowerException("The borrowed amount must be a minimum and a maximum of 1.");
        }

        if (optionalStock.get().getAmountOfStock() <= 0) {
            throw new BorrowerException("The minimum value of the borrowed amount cannot be less than 0.");
        }

        Long numberOfAvailableStocks = optionalStock.get().getBook().getAmountOfStock();
        Long newStockCount = optionalStock.get().getAmountOfStock() + 1;

        if (!borrowerUpdateRequestDto.isStatus()) {
            getUpdateStock(borrowerUpdateRequestDto.getBookId(), borrowerUpdateRequestDto.getStockId(), newStockCount);
        }

        if (numberOfAvailableStocks < newStockCount) {
            throw new BorrowerException("Incorrect book stock quantity.");
        }

        Borrower save = getBorrowerSave(borrowerMapper.mapToBorrower(borrowerUpdateRequestDto));

        getUpdateStock(borrowerUpdateRequestDto.getBookId(),
                borrowerUpdateRequestDto.getStockId(),
                (numberOfAvailableStocks - 1));


        log.info("BookService::updateBook finished.");
        return borrowerMapper.mapToBorrowerResponseDto(save);
    }

    private Borrower getBorrowerSave(Borrower borrowerMapper) {
        Borrower borrower = borrowerMapper;
        Borrower save = borrowerRepository.save(borrower);
        return save;
    }

    public void deleteBook(long id) {
        log.info("BorrowerService::deleteBook started.");

        checkOptionalBorrowerById(id);

        log.info("BorrowerService::deleteBook finished.");
        borrowerRepository.deleteById(id);
    }

    private void checkOptionalBorrowerById(Long borrowerId) {
        Optional<Borrower> optionalBorrower = borrowerRepository.findById(borrowerId);
        optionalBorrower.orElseThrow(() -> new BorrowerException("Borrower not found id : " + borrowerId));
    }

    private void checkOptionalMemberById(Long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        optionalMember.orElseThrow(() -> new MemberException("Member not found id : " + memberId));
    }

    private void checkOptionalBookById(Long bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        optionalBook.orElseThrow(() -> new BookException("Book not found id : " + bookId));
    }

    private Optional<Stock> checkOptionalStockId(Long stockId) {
        Optional<Stock> optionalStock = stockRepository.findById(stockId);
        optionalStock.orElseThrow(() -> new StockException("Stock not found for id : " + stockId));
        return optionalStock;
    }

    private void getUpdateStock(Long bookId, Long stockId, Long amountOfStock) {
        stockService.updateStock(StockUpdateRequestDto.builder().id(stockId)
                .bookId(bookId)
                .amountOfStock(amountOfStock)
                .build());
    }
}
