package com.example.springbootlibraryproject.repository;

import com.example.springbootlibraryproject.entity.Book;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByName(String name, PageRequest pageRequest);

    List<Book> findByName(String name, Sort sort, PageRequest pageRequest);

    List<Book> findByCreationDateOrderByNameAsc(LocalDate creationDate);

    List<Book> findByCreationDateOrderByNameDesc(LocalDate creationDate);

    List<Book> findByAmountOfStockGreaterThanEqualOrderByNameAsc(long amountOfStock);

    List<Book> findByAmountOfStockGreaterThanEqualOrderByNameDesc(long amountOfStock);


}