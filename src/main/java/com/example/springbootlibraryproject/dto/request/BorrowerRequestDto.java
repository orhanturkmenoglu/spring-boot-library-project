package com.example.springbootlibraryproject.dto.request;

import com.example.springbootlibraryproject.entity.Book;
import com.example.springbootlibraryproject.entity.Member;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowerRequestDto {

    @NotNull(message = "Borrower member must not be null")
    private Member member;

    @NotNull(message = "Borrower book must not be null")
    private Book book;

    @NotNull(message = " Borrower status must not be null")
    private boolean status;

    @NotNull(message = "Borrower date must not be null")
    private LocalDate date;

    @NotNull(message = "Borrower return date must not be null")
    private LocalDate returnDate;
}
