package com.example.springbootlibraryproject.mapper;

import com.example.springbootlibraryproject.dto.request.BorrowerRequestDto;
import com.example.springbootlibraryproject.dto.response.BorrowerResponseDto;
import com.example.springbootlibraryproject.dto.updateRequest.BorrowerUpdateRequestDto;
import com.example.springbootlibraryproject.entity.Borrower;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BorrowerMapper {

    public List<BorrowerResponseDto> mapToBorrowerResponseDtoList(List<Borrower> borrowerList) {
        return borrowerList
                .stream()
                .map(this::mapToBorrowerResponseDto)
                .collect(Collectors.toList());
    }

    public Borrower mapToBorrower(BorrowerRequestDto borrowerRequestDto) {
        return Borrower.builder()
                .member(borrowerRequestDto.getMember())
                .book(borrowerRequestDto.getBook())
                .status(borrowerRequestDto.isStatus())
                .date(borrowerRequestDto.getDate())
                .returnDate(borrowerRequestDto.getReturnDate())
                .build();
    }

    public Borrower mapToBorrower(BorrowerUpdateRequestDto borrowerUpdateRequestDto) {
        return Borrower.builder()
                .id(borrowerUpdateRequestDto.getId())
                .member(borrowerUpdateRequestDto.getMember())
                .book(borrowerUpdateRequestDto.getBook())
                .status(borrowerUpdateRequestDto.isStatus())
                .date(borrowerUpdateRequestDto.getDate())
                .returnDate(borrowerUpdateRequestDto.getReturnDate())
                .build();
    }

    public BorrowerResponseDto mapToBorrowerResponseDto(Borrower borrower) {
        return BorrowerResponseDto.builder()
                .id(borrower.getId())
                .member(borrower.getMember())
                .book(borrower.getBook())
                .status(borrower.isStatus())
                .date(borrower.getDate())
                .returnDate(borrower.getReturnDate())
                .build();
    }
}
