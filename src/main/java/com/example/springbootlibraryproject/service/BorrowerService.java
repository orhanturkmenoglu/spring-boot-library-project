package com.example.springbootlibraryproject.service;

import com.example.springbootlibraryproject.dto.request.BorrowerRequestDto;
import com.example.springbootlibraryproject.dto.response.BorrowerResponseDto;
import com.example.springbootlibraryproject.entity.Borrower;
import com.example.springbootlibraryproject.mapper.BorrowerMapper;
import com.example.springbootlibraryproject.repository.BorrowerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BorrowerService {

    private final BorrowerRepository borrowerRepository;
    private final BorrowerMapper borrowerMapper;

    public List<BorrowerResponseDto> getBorrowersAll() {
        List<Borrower> borrowerList = borrowerRepository.findAll();
        return borrowerMapper.mapToBorrowerResponseDtoList(borrowerList);
    }

    public BorrowerResponseDto createBorrower(BorrowerRequestDto borrowerRequestDto) {
        Borrower borrower = borrowerMapper.mapToBorrowerResponseDto(borrowerRequestDto);
        Borrower save = borrowerRepository.save(borrower);
        return borrowerMapper.mapToBorrowerResponseDto(save);
    }
}
