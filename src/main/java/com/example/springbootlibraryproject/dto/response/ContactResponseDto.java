package com.example.springbootlibraryproject.dto.response;

import com.example.springbootlibraryproject.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactResponseDto {

    private Long id;

    private String city;
    private String district;
    private String address;
    private String phoneNumber;
    private String email;
    private Member member;
}
