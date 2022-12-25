package com.example.springbootlibraryproject.dto.updateRequest;

import com.example.springbootlibraryproject.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactUpdateRequestDto {

    @NotNull(message = "Contact id must not be null")
    private Long id;

    @NotBlank(message = "Contact  city must not be null")
    private String city;

    @NotBlank(message = "Contact district not be null")
    private String district;

    @NotBlank(message = "Contact address not be null")
    @Length(max = 255, message = "Contact address maximum length must be 255 characters")
    private String address;

    @Pattern(regexp = "^(\\+)?(90)?(5\\d{2})(\\d{3})(\\d{2})(\\d{2})$", message = "Phone Number is not valid ")
    private String phoneNumber;

    @NotBlank(message = "Contact Email must not be null or empty")
    @Email(message = "Contact Email is not valid ", regexp = "^(.+)@(.+)$")
    private String email;

    private Member member;

}
