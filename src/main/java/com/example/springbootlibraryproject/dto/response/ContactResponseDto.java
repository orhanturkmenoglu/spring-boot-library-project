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
public class ContactResponseDto implements Serializable {

    private Long id;
    private String city;
    private String district;
    private String address;
}
