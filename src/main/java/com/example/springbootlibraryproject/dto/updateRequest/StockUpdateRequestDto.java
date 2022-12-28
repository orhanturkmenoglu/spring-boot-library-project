package com.example.springbootlibraryproject.dto.updateRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class StockUpdateRequestDto {

    @NotNull(message = "Stock id must not be null")
    private Long id;

    @NotNull(message = "Stock book id must not be null")
    private Long bookId;

    @NotNull(message = "Stock amount of stock must not be null")
    private Long amountOfStock;
}
