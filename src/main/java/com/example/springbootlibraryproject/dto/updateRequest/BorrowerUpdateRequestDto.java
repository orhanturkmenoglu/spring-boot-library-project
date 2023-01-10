package com.example.springbootlibraryproject.dto.updateRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowerUpdateRequestDto implements Serializable {

    @NotNull(message = "Borrower id must not be null")
    private Long id;

    @Builder.Default
    private boolean status = false;

    @NotNull(message = "Borrower date must not be null")
    private LocalDate date;

    @NotNull(message = "Borrower return date must not be null")
    @Builder.Default
    private LocalDate returnDate = LocalDate.now().plusDays(15);

    @NotNull(message = "Borrower member  must not be null")
    private Long memberId;

    @NotNull(message = "Borrower book id  must not be null")
    private Long bookId;

    @NotNull(message = "Borrower stock id must not be null")
    private Long stockId;

    @NotNull(message = "Borrower amount borrowed must not be null")

    @Min(value = 1, message = "The borrowed amount must be a minimum and a maximum of 1")
    @Max(value = 1, message = "The borrowed amount must be a minimum and a maximum of 1")
    private Long amountBorrowed;
}
