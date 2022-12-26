package com.example.springbootlibraryproject.dto.request;

import com.example.springbootlibraryproject.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockRequestDto {

    private Book book;

    private Long amountOfStock;
}
