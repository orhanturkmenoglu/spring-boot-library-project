package com.example.springbootlibraryproject.mapper;

import com.example.springbootlibraryproject.dto.request.BorrowerRequestDto;
import com.example.springbootlibraryproject.dto.response.BorrowerResponseDto;
import com.example.springbootlibraryproject.dto.updateRequest.BorrowerUpdateRequestDto;
import com.example.springbootlibraryproject.entity.Book;
import com.example.springbootlibraryproject.entity.Borrower;
import com.example.springbootlibraryproject.entity.Member;
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
                .member(Member.builder().id(borrowerRequestDto.getMemberId()).build())
                .book(Book.builder().id(borrowerRequestDto.getBookId()).build())
                .status(borrowerRequestDto.isStatus())
                .date(borrowerRequestDto.getDate())
                .returnDate(borrowerRequestDto.getReturnDate().plusDays(15))
                .amountBorrowed(borrowerRequestDto.getAmountBorrowed())
                .build();
    }

    public Borrower mapToBorrower(BorrowerUpdateRequestDto borrowerUpdateRequestDto) {
        return Borrower.builder()
                .id(borrowerUpdateRequestDto.getId())
                .member(Member.builder().id(borrowerUpdateRequestDto.getMemberId()).build())
                .book(Book.builder().id(borrowerUpdateRequestDto.getBookId()).build())
                .status(borrowerUpdateRequestDto.isStatus())
                .date(borrowerUpdateRequestDto.getDate())
                .returnDate(borrowerUpdateRequestDto.getReturnDate())
                .amountBorrowed(borrowerUpdateRequestDto.getAmountBorrowed())
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
