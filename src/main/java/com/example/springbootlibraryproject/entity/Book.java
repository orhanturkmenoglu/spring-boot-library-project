package com.example.springbootlibraryproject.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String barcode;
    private String name;
    private String author;
    private String publisher;
    private Long numbersOfPages;
    private LocalDate creationDate;
    private Long amountOfStock;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Stock stock;
}