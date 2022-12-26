package com.example.springbootlibraryproject.dto.response;

import com.example.springbootlibraryproject.entity.Book;
import com.example.springbootlibraryproject.entity.Member;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class BorrowerResponseDto implements Serializable {

    private Long id;
    private Member member;
    private Book book;
    private boolean status;
    private LocalDate date;
    private LocalDate returnDate;

}
