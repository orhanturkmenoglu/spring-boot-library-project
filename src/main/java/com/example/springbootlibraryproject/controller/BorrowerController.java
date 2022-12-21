package com.example.springbootlibraryproject.controller;

import com.example.springbootlibraryproject.dto.request.BorrowerRequestDto;
import com.example.springbootlibraryproject.dto.response.BorrowerResponseDto;
import com.example.springbootlibraryproject.service.BorrowerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrowers")
@RequiredArgsConstructor
@Slf4j
public class BorrowerController {

    private final BorrowerService borrowerService;

    @GetMapping
    public ResponseEntity<List<BorrowerResponseDto>> getBorrowersAll() {
        List<BorrowerResponseDto> borrowerResponseDtoList = borrowerService.getBorrowersAll();
        return ResponseEntity.ok().body(borrowerResponseDtoList);
    }

    @PostMapping
    public ResponseEntity<BorrowerResponseDto> createBorrower(@RequestBody BorrowerRequestDto borrowerRequestDto) {
        BorrowerResponseDto borrowerResponseDto = borrowerService.createBorrower(borrowerRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(borrowerResponseDto);
    }
}
