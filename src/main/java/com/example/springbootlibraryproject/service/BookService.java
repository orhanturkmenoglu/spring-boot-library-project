package com.example.springbootlibraryproject.service;

import com.example.springbootlibraryproject.dto.request.BookRequestDto;
import com.example.springbootlibraryproject.dto.response.BookResponseDto;
import com.example.springbootlibraryproject.entity.Book;
import com.example.springbootlibraryproject.mapper.BookMapper;
import com.example.springbootlibraryproject.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    public List<BookResponseDto> getBooksAll() {
        List<Book> bookList = bookRepository.findAll();
        return bookMapper.mapToBookResponseDtoList(bookList);
    }


    public BookResponseDto createBook(BookRequestDto bookRequestDto) {
        Book book = bookMapper.mapToBook(bookRequestDto);
        Book save = bookRepository.save(book);
        return bookMapper.mapToBookResponseDto(save);
    }
}
