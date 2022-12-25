package com.example.springbootlibraryproject.dto.updateRequest;

import com.example.springbootlibraryproject.entity.Contact;
import com.example.springbootlibraryproject.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberUpdateRequestDto {

    @NotNull(message = "Member id must not be null")
    private Long id;

    @NotBlank(message = "Member first name must not be null or empty")
    @Length(min = 2, message = "Member first name minimum length must be 2 characters")
    private String firstName;

    @NotBlank(message = "Member last name must not be null or empty")
    @Length(min = 2, message = "Member last name minimum length must be 2 characters")
    private String lastName;

    @NotBlank(message = "Member identityNo must not be null or empty")
    private String identityNo;

    @NotBlank(message = "Member gender must not be null or empty")
    private Gender gender;
}
