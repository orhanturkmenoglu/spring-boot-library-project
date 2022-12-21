package com.example.springbootlibraryproject.controller;

import com.example.springbootlibraryproject.dto.request.MemberRequestDto;
import com.example.springbootlibraryproject.dto.response.MemberResponseDto;
import com.example.springbootlibraryproject.enums.Gender;
import com.example.springbootlibraryproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        return ResponseEntity.status(HttpStatus.CREATED).body(memberResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> getMembersAll() {
        List<MemberResponseDto> memberResponseDtoList = memberService.getMembersAll();
        return ResponseEntity.ok().body(memberResponseDtoList);
    }

    @GetMapping("/getMembersName/{name}")
    public ResponseEntity<List<MemberResponseDto>> getMembersByName(@Valid @PathVariable(value = "name", required = false) String name) {
        List<MemberResponseDto> memberResponseDtoList = memberService.getMembersByName(name);
        return ResponseEntity.ok().body(memberResponseDtoList);
    }


    @GetMapping("/getMembersGender")
    public ResponseEntity<List<MemberResponseDto>> getMembersByGender(@RequestParam("gender") Gender gender) {
        List<MemberResponseDto> memberResponseDtoList = memberService.getMembersByGender(gender);
        return ResponseEntity.ok().body(memberResponseDtoList);
    }
}
