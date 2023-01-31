package com.example.springbootlibraryproject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageResponseDto implements Serializable {

    private String name;
    private String type;
    private byte[] image;
}
