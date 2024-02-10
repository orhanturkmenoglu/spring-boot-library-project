package com.example.springbootlibraryproject.service;

import com.example.springbootlibraryproject.dto.request.BookRequestDto;
import com.example.springbootlibraryproject.dto.response.BookResponseDto;
import com.example.springbootlibraryproject.dto.updateRequest.BookUpdateRequestDto;
import com.example.springbootlibraryproject.entity.Book;
import com.example.springbootlibraryproject.exceptions.BookException;
import com.example.springbootlibraryproject.mapper.BookMapper;
import com.example.springbootlibraryproject.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookResponseDto createBook(BookRequestDto bookRequestDto) {
        log.info("BookService::createBook started");

        Book book = bookMapper.mapToBook(bookRequestDto);
        Book save = bookRepository.save(book);

        log.info("BookService::createBook finished");
        return bookMapper.mapToBookResponseDto(save);
    }
    @Cacheable(value = "book")
    public List<BookResponseDto> getBooksAll() {
        log.info("BookService::getBooksAll started");

        List<Book> bookList = bookRepository.findAll();

        log.info("BookService::getBooksAll finished");
        return bookMapper.mapToBookResponseDtoList(bookList);
    }

    @Cacheable(value = "book", key = "#name")
    public List<BookResponseDto> getBooksByName(String name, String sortBy, int page, int pageSize) {
        log.info("BookService::getBooksByName started");

        List<Book> bookList;
        PageRequest pageRequest = PageRequest.of(page, pageSize);

        if (StringUtils.isEmpty(name) || StringUtils.isBlank(name)) {
            throw new NullPointerException("Book name cannot be empty or null");
        }

        if (!(name.equalsIgnoreCase(name))) {
            throw new BookException("Book name is not a valid");
        }

        if (StringUtils.isNotBlank(sortBy)) {
            bookList = bookRepository.findByName(name, Sort.by(sortBy), pageRequest);
            return bookMapper.mapToBookResponseDtoList(bookList);
        }

        bookList = bookRepository.findByName(name, pageRequest);

        log.info("BookService::getBooksByName finished");
        return bookMapper.mapToBookResponseDtoList(bookList);
    }

    public List<BookResponseDto> getBooksCreationDateOrderByAsc(String creationDate) {
        log.info("BookService::getBooksCreationDateOrderByAsc started");

        if (StringUtils.isEmpty(creationDate) && StringUtils.isBlank(creationDate)) {
            throw new NullPointerException("Book creation date must not be empty");
        }

        List<Book> bookList = bookRepository.findByCreationDateOrderByNameAsc((LocalDate.parse(creationDate)));

        log.info("BookService::getBooksCreationDateOrderByAsc finished");
        return bookMapper.mapToBookResponseDtoList(bookList);
    }

    public List<BookResponseDto> getBooksCreationDateOrderByDesc(String creationDate) {
        log.info("BookService::getBooksCreationDateOrderByDesc started");

        if (StringUtils.isEmpty(creationDate) && StringUtils.isBlank(creationDate)) {
            throw new NullPointerException("Book creation date must not be empty");
        }

        List<Book> bookList = bookRepository.findByCreationDateOrderByNameDesc(LocalDate.parse(creationDate));

        log.info("BookService::getBooksCreationDateOrderByDesc finished");
        return bookMapper.mapToBookResponseDtoList(bookList);
    }

    public List<BookResponseDto> getBooksAmountOfStockGreaterThanEqualOrderByNameAsc(long amountOfStock) {
        log.info("BookService::getBooksAmountOfStockGreaterThanEqualOrderByNameAsc started");

        List<Book> bookList = bookRepository.findByAmountOfStockGreaterThanEqualOrderByNameAsc(amountOfStock);

        log.info("BookService::getBooksAmountOfStockGreaterThanEqualOrderByNameAsc finished");
        return bookMapper.mapToBookResponseDtoList(bookList);
    }

    public List<BookResponseDto> getBooksAmountOfStockGreaterThanEqualOrderByNameDesc(long amountOfStock) {
        log.info("BookService::getBooksAmountOfStockGreaterThanEqualOrderByNameDesc started");

        List<Book> bookList = bookRepository.findByAmountOfStockGreaterThanEqualOrderByNameDesc(amountOfStock);

        log.info("BookService::getBooksAmountOfStockGreaterThanEqualOrderByNameDesc finished");
        return bookMapper.mapToBookResponseDtoList(bookList);
    }

    @CachePut(value = "book", key = "#bookUpdateRequestDto")
    public BookResponseDto updateBook(BookUpdateRequestDto bookUpdateRequestDto) {
        log.info("BookService::updateBook started");

        Optional<Book> optionalBook = bookRepository.findById(bookUpdateRequestDto.getId());
        optionalBook.orElseThrow(() -> new BookException("Book not found id : " + bookUpdateRequestDto.getId()));

        Book book = bookMapper.mapToBook(bookUpdateRequestDto);
        Book save = bookRepository.save(book);

        log.info("BookService::updateBook finished");
        return bookMapper.mapToBookResponseDto(save);
    }

    @CacheEvict(value = "book", key = "#id")
    public void deleteBook(long id) {
        log.info("BookService::deleteBook started");

        Optional<Book> optionalBook = bookRepository.findById(id);
        optionalBook.orElseThrow(() -> new BookException("Book not found id : " + id));

        bookRepository.deleteById(id);
        log.info("BookService::deleteBook finished");
    }
}