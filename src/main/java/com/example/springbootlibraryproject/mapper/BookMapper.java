package com.example.springbootlibraryproject.mapper;

import com.example.springbootlibraryproject.dto.request.BookRequestDto;
import com.example.springbootlibraryproject.dto.response.BookResponseDto;
import com.example.springbootlibraryproject.dto.updateRequest.BookUpdateRequestDto;
import com.example.springbootlibraryproject.entity.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {
    public List<BookResponseDto> mapToBookResponseDtoList(List<Book> bookList) {
        return bookList
                .stream()
                .map(this::mapToBookResponseDto)
                .collect(Collectors.toList());
    }

    public Book mapToBook(BookRequestDto bookRequestDto) {
        return Book.builder()
                .barcode(bookRequestDto.getBarcode())
                .name(bookRequestDto.getName())
                .author(bookRequestDto.getAuthor())
                .publisher(bookRequestDto.getPublisher())
                .numbersOfPages(bookRequestDto.getNumbersOfPages())
                .creationDate(bookRequestDto.getCreationDate())
                .amountOfStock(bookRequestDto.getAmountOfStock())
                .build();
    }

    public Book mapToBook(BookUpdateRequestDto bookUpdateRequestDto) {
        return Book.builder()
                .id(bookUpdateRequestDto.getId())
                .barcode(bookUpdateRequestDto.getBarcode())
                .name(bookUpdateRequestDto.getName())
                .author(bookUpdateRequestDto.getAuthor())
                .publisher(bookUpdateRequestDto.getPublisher())
                .numbersOfPages(bookUpdateRequestDto.getNumbersOfPages())
                .creationDate(bookUpdateRequestDto.getCreationDate())
                .amountOfStock(bookUpdateRequestDto.getAmountOfStock())
                .build();
    }

    public BookResponseDto mapToBookResponseDto(Book book) {
        return BookResponseDto.builder()
                .id(book.getId())
                .barcode(book.getBarcode())
                .name(book.getName())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .numbersOfPages(book.getNumbersOfPages())
                .creationDate(book.getCreationDate())
                .amountOfStock(book.getAmountOfStock())
                .build();
    }


}
