package com.example.springbootlibraryproject.controller;

import com.example.springbootlibraryproject.constants.MemberMessage;
import com.example.springbootlibraryproject.dto.request.MemberRequestDto;
import com.example.springbootlibraryproject.dto.response.MemberResponseDto;
import com.example.springbootlibraryproject.dto.updateRequest.MemberUpdateRequestDto;
import com.example.springbootlibraryproject.enums.Gender;
import com.example.springbootlibraryproject.exceptions.ErrorResponse;
import com.example.springbootlibraryproject.exceptions.SuccessResponse;
import com.example.springbootlibraryproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberResponseDto> createMember(@RequestBody MemberRequestDto memberRequestDto) {
        MemberResponseDto memberResponseDto = memberService.createMember(memberRequestDto);
        return SuccessResponse.responseBuilder(MemberMessage.CREATE_MEMBER, HttpStatus.CREATED, memberResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> getMembersAll(@RequestParam(value = "firstName", required = false) String firstName) {

        if (LocalDateTime.now().getHour() == 11) {
            return ErrorResponse.responseBuilder(MemberMessage.SERVICE_UNAVAILABLE, HttpStatus.SERVICE_UNAVAILABLE);
        }
        List<MemberResponseDto> memberResponseDtoList = memberService.getMembersAll(firstName);
        return SuccessResponse.responseBuilder(MemberMessage.GET_MEMBERS_ALL, HttpStatus.OK, memberResponseDtoList);
    }

    @GetMapping("/getMembersGender")
    public ResponseEntity<List<MemberResponseDto>> getMembersByGender(@RequestParam("gender") Gender gender) {
        List<MemberResponseDto> memberResponseDtoList = memberService.getMembersByGender(gender);
        return SuccessResponse.responseBuilder(MemberMessage.GET_MEMBERS_BY_GENDER, HttpStatus.OK, memberResponseDtoList);
    }

    @PutMapping
    public ResponseEntity<MemberResponseDto> updateBook(@Valid @RequestBody MemberUpdateRequestDto memberUpdateRequestDto) {
        MemberResponseDto memberResponseDto = memberService.updateBook(memberUpdateRequestDto);
        return SuccessResponse.responseBuilder(MemberMessage.UPDATE_MEMBER, HttpStatus.CREATED, memberResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") long id) {
        memberService.deleteBook(id);
        return SuccessResponse.responseBuilder(MemberMessage.DELETE_MEMBER_BY_ID, HttpStatus.OK);
    }
}
