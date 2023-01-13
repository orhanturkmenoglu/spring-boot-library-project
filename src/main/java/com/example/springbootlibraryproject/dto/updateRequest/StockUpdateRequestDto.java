package com.example.springbootlibraryproject.dto.updateRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class StockUpdateRequestDto implements Serializable {

    @NotNull(message = "Stock id must not be null")
    private Long id;

    @NotNull(message = "Stock amount of stock must not be null")
    private Long amountOfStock;
}
