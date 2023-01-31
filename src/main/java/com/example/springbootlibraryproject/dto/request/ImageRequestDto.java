package com.example.springbootlibraryproject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageRequestDto implements Serializable {

    @NotBlank(message = "Image publisher must not be null or empty")
    private String image;
}
