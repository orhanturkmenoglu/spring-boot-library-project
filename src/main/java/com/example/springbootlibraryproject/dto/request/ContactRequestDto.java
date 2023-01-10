package com.example.springbootlibraryproject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactRequestDto implements Serializable {

    @NotBlank(message = "Contact  city must not be null")
    private String city;

    @NotBlank(message = "Contact district not be null")
    private String district;

    @NotBlank(message = "Contact address not be null")
    @Length(max = 255, message = "Contact address maximum length must be 255 characters")
    private String address;
}
