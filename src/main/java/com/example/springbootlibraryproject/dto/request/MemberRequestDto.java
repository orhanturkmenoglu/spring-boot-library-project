package com.example.springbootlibraryproject.dto.request;

import com.example.springbootlibraryproject.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberRequestDto implements Serializable {

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

    @Pattern(regexp = "^(\\+)?(90)?(5\\d{2})(\\d{3})(\\d{2})(\\d{2})$", message = "Phone Number is not valid ")
    private String phoneNumber;

    @NotBlank(message = "Contact Email must not be null or empty")
    @Email(message = "Contact Email is not valid ", regexp = "^(.+)@(.+)$")
    private String email;

    private ContactRequestDto contact;
}
