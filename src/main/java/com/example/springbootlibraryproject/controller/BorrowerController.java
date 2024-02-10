package com.example.springbootlibraryproject.controller;

import com.example.springbootlibraryproject.constants.BorrowerMessage;
import com.example.springbootlibraryproject.dto.request.BorrowerRequestDto;
import com.example.springbootlibraryproject.dto.response.BorrowerResponseDto;
import com.example.springbootlibraryproject.dto.updateRequest.BorrowerUpdateRequestDto;
import com.example.springbootlibraryproject.exceptions.SuccessResponse;
import com.example.springbootlibraryproject.service.BorrowerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/borrowers")
@RequiredArgsConstructor
@Slf4j
public class BorrowerController {

    private final BorrowerService borrowerService;

    @PostMapping("/createBorrower")
    public ResponseEntity<BorrowerResponseDto> createBorrower(@RequestBody BorrowerRequestDto borrowerRequestDto) {
        BorrowerResponseDto borrowerResponseDto = borrowerService.createBorrower(borrowerRequestDto);
        return SuccessResponse.responseBuilder(BorrowerMessage.CREATE_BOOK, HttpStatus.CREATED, borrowerResponseDto);
    }

    @GetMapping("/borrowersAll")
    public ResponseEntity<List<BorrowerResponseDto>> getBorrowersAll() {
        List<BorrowerResponseDto> borrowerResponseDtoList = borrowerService.getBorrowersAll();
        return SuccessResponse.responseBuilder(BorrowerMessage.GET_BORROWERS_ALL, HttpStatus.OK, borrowerResponseDtoList);
    }

    @PutMapping("/updateBorrower")
    public ResponseEntity<BorrowerResponseDto> updateBorrower(@Valid @RequestBody BorrowerUpdateRequestDto borrowerUpdateRequestDto) {
        BorrowerResponseDto borrowerResponseDto = borrowerService.updateBorrower(borrowerUpdateRequestDto);
        return SuccessResponse.responseBuilder(BorrowerMessage.UPDATE_BORROWER, HttpStatus.CREATED, borrowerResponseDto);
    }

    @DeleteMapping("deleteBorrower/{id}")
    public ResponseEntity<Void> deleteBorrower(@PathVariable("id") long id) {
        borrowerService.deleteBook(id);
        return SuccessResponse.responseBuilder(BorrowerMessage.DELETE_BORROWER_BY_ID, HttpStatus.OK);
    }


}
