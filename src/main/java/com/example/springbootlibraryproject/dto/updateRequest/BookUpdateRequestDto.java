package com.example.springbootlibraryproject.dto.updateRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookUpdateRequestDto implements Serializable {

    @NotNull(message = "Book id must not be null")
    private Long id;

    @NotBlank(message = "Book must not be null or empty ")
    @Length(min = 13, max = 13, message = "Book  barcode must be 13 characters")
    private String barcode;

    @NotBlank(message = "Book name must not be null or empty")
    @Length(min = 2, message = "Book name minimum length must be 2 characters")
    private String name;

    @NotBlank(message = "Book author must not be null or empty")
    @Length(min = 2, message = "Book author minimum length must be 2 characters")
    private String author;

    @NotBlank(message = "Book publisher must not be null or empty")
    @Length(min = 2, message = "Book publisher minimum length must be 2 characters")
    private String publisher;

    @NotNull(message = "Book number of pages must be null")
    @Min(value = 1, message = "Book number of pages must be minimum value 1")
    private Long numbersOfPages;

    @NotNull(message = "Book creation date must be null")
    private LocalDate creationDate;

    @NotNull(message = "Book amount of stock  must be null")
    private Long amountOfStock;

   /* private StockUpdateRequestDto stockUpdateRequestDto;*/
}
