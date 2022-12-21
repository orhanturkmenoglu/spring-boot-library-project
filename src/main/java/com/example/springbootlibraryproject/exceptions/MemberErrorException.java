package com.example.springbootlibraryproject.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberErrorException {

    private int status;
    private String message;
    private String error;
    private LocalDate timestamp;
    private String path;

}
