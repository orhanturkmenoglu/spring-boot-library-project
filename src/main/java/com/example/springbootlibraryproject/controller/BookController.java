package com.example.springbootlibraryproject.controller;

import com.example.springbootlibraryproject.constants.BookMessage;
import com.example.springbootlibraryproject.dto.request.BookRequestDto;
import com.example.springbootlibraryproject.dto.response.BookResponseDto;
import com.example.springbootlibraryproject.dto.updateRequest.BookUpdateRequestDto;
import com.example.springbootlibraryproject.exceptions.SuccessResponse;
import com.example.springbootlibraryproject.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<BookResponseDto> createBook(@Valid @RequestBody BookRequestDto bookRequestDto) {
        BookResponseDto bookResponseDto = bookService.createBook(bookRequestDto);
        return SuccessResponse.responseBuilder(BookMessage.CREATE_BOOK, HttpStatus.CREATED, bookResponseDto);
    }

    @GetMapping("/booksAll")
    public ResponseEntity<List<BookResponseDto>> getBooksAll() {
        List<BookResponseDto> bookResponseDtoList = bookService.getBooksAll();
        return SuccessResponse.responseBuilder(BookMessage.GET_BOOKS_ALL, HttpStatus.OK, bookResponseDtoList);
    }

    @GetMapping("/getBooksCreationDateAsc")
    public ResponseEntity<List<BookResponseDto>> getBooksCreationDateAsc(@RequestParam(value = "creationDate") String creationDate) {

        List<BookResponseDto> bookResponseDtoList = bookService.getBooksCreationDateOrderByAsc(creationDate);
        return SuccessResponse.responseBuilder(BookMessage.GET_BOOKS_CREATION_DATE_ASC, HttpStatus.OK, bookResponseDtoList);
    }

    @GetMapping("/getBooksCreationDateDesc")
    public ResponseEntity<List<BookResponseDto>> getBooksCreationDateDesc(@RequestParam(value = "creationDate") String creationDate) {

        List<BookResponseDto> bookResponseDtoList = bookService.getBooksCreationDateOrderByDesc(creationDate);
        return SuccessResponse.responseBuilder(BookMessage.GET_BOOKS_CREATION_DATE_DESC, HttpStatus.OK, bookResponseDtoList);
    }

    @GetMapping("/getBooksByName")
    public ResponseEntity<List<BookResponseDto>> getBooksByName(@RequestParam(value = "name") String name,
                                                                @RequestParam(value = "sortBy", required = false) String sortBy,
                                                                @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                                @RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize) {

        List<BookResponseDto> bookResponseDtoList = bookService.getBooksByName(name, sortBy, page, pageSize);
        return SuccessResponse.responseBuilder(BookMessage.GET_BOOKS_BY_NAME, HttpStatus.OK, bookResponseDtoList);
    }

    @GetMapping("/getBooksAmountOfStockOrderByAsc")
    public ResponseEntity<List<BookResponseDto>> getBooksAmountOfStockGreaterThanEqualOrderByNameAsc(@RequestParam("amountOfStock") long amountOfStock) {
        List<BookResponseDto> bookList = bookService.getBooksAmountOfStockGreaterThanEqualOrderByNameAsc(amountOfStock);
        return SuccessResponse.responseBuilder(BookMessage.GET_BOOKS_AMOUNT_OF_STOCK_ASC, HttpStatus.OK, bookList);
    }

    @GetMapping("/getBooksAmountOfStockOrderByDesc")
    public ResponseEntity<List<BookResponseDto>> getBooksAmountOfStockGreaterThanEqualOrderByNameDesc(@RequestParam("amountOfStock") long amountOfStock) {
        List<BookResponseDto> bookList = bookService.getBooksAmountOfStockGreaterThanEqualOrderByNameDesc(amountOfStock);
        return SuccessResponse.responseBuilder(BookMessage.GET_BOOKS_AMOUNT_OF_STOCK_DESC, HttpStatus.OK, bookList);
    }

    @PutMapping("/updateBook")
    public ResponseEntity<BookResponseDto> updateBook(@Valid @RequestBody BookUpdateRequestDto bookUpdateRequestDto) {
        BookResponseDto bookResponseDto = bookService.updateBook(bookUpdateRequestDto);
        return SuccessResponse.responseBuilder(BookMessage.UPDATE_BOOK, HttpStatus.OK, bookResponseDto);
    }

    @DeleteMapping("deleteBook/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") long id) {
        bookService.deleteBook(id);
        return SuccessResponse.responseBuilder(BookMessage.DELETE_BOOK_BY_ID, HttpStatus.OK);
    }
}
