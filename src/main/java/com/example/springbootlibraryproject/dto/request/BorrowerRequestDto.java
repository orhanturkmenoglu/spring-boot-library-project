package com.example.springbootlibraryproject.dto.request;

import com.example.springbootlibraryproject.entity.Book;
import com.example.springbootlibraryproject.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowerRequestDto {

    @NotNull(message = " Borrower status must not be null")
    private boolean status;

    @NotNull(message = "Borrower date must not be null")
    private LocalDate date;

    @NotNull(message = "Borrower return date must not be null")
    private LocalDate returnDate;

    @NotNull(message = "Borrower member id must not be null")
    private Member member;

    @NotNull(message = "Borrower book id must not be null")
    private Book book;

    @NotNull(message = "Borrower amount borrowed must not be null")

    @Min(value = 1, message = "The borrowed amount must be a minimum and a maximum of 1")
    @Max(value = 1, message = "The borrowed amount must be a minimum and a maximum of 1")
    private Long amountBorrowed;

}
