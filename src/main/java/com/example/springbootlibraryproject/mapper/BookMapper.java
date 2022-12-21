package com.example.springbootlibraryproject.mapper;

import com.example.springbootlibraryproject.dto.request.BookRequestDto;
import com.example.springbootlibraryproject.dto.response.BookResponseDto;
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
                .dateOfIssue(bookRequestDto.getDateOfIssue())
                .creationDate(bookRequestDto.getCreationDate())
                .amountOfStock(bookRequestDto.getAmountOfStock())
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
                .dateOfIssue(book.getDateOfIssue())
                .creationDate(book.getCreationDate())
                .amountOfStock(book.getAmountOfStock())
                .build();
    }


}
