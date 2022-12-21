package com.example.springbootlibraryproject.dto.response;

import com.example.springbootlibraryproject.entity.Contact;
import com.example.springbootlibraryproject.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberResponseDto implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String identityNo;
    private Gender gender;
    private Contact contact;
}
