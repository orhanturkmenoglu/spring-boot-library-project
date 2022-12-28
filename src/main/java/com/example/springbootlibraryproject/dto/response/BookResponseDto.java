package com.example.springbootlibraryproject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponseDto implements Serializable {
    private Long id;
    private String barcode;
    private String name;
    private String author;
    private String publisher;
    private Long numbersOfPages;
    private LocalDate creationDate;
    private Long amountOfStock;
}
