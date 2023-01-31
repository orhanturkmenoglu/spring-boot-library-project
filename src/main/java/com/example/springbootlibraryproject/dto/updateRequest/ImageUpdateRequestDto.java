package com.example.springbootlibraryproject.dto.updateRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageUpdateRequestDto {

    @NotNull(message = "Image id must not be null")
    private Long id;

    @NotBlank(message = "Image name must not be null or empty")
    private String image;
}
