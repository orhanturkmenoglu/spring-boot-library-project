package com.example.springbootlibraryproject.dto.response;

import com.example.springbootlibraryproject.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockResponseDto {

    private Book book;

    private Long amountOfStock;
}
