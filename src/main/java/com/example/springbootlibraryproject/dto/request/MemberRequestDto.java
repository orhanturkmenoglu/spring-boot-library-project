package com.example.springbootlibraryproject.dto.request;

import com.example.springbootlibraryproject.entity.Contact;
import com.example.springbootlibraryproject.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberRequestDto {

    private String firstName;
    private String lastName;
    private String identityNo;
    private Gender gender;
    private Contact contact;
}
