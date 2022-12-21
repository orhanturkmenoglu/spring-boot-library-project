package com.example.springbootlibraryproject.service;

import com.example.springbootlibraryproject.dto.request.MemberRequestDto;
import com.example.springbootlibraryproject.dto.response.MemberResponseDto;
import com.example.springbootlibraryproject.entity.Member;
import com.example.springbootlibraryproject.enums.Gender;
import com.example.springbootlibraryproject.exceptions.MemberException;
import com.example.springbootlibraryproject.mapper.MemmerMapper;
import com.example.springbootlibraryproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    private final MemmerMapper memmerMapper;

    public List<MemberResponseDto> getMembersAll() {
        List<Member> memberList = memberRepository.findAll();
        return memmerMapper.mapToMemberResponseDtoList(memberList);
    }

    public MemberResponseDto createMember(MemberRequestDto memberRequestDto) {
        Member member = memmerMapper.mapToMember(memberRequestDto);
        Member save = memberRepository.save(member);
        return memmerMapper.mapToMemberResponseDto(save);
    }

    public List<MemberResponseDto> getMembersByName(String name) {
        if (StringUtils.isEmpty(name) || StringUtils.isBlank(name)) {
            throw new MemberException("Member name must not  be null or empty");
        }

        List<Member> memberList = memberRepository.findMemberByFirstName(name);
        return memmerMapper.mapToMemberResponseDtoList(memberList);
    }

    public List<MemberResponseDto> getMembersByGender(Gender gender) {
        List<Member> memberList ;
        if (Objects.isNull(gender)) {
            throw new MemberException("Member gender must not be null or empty");
        }

        memberList = memberRepository.findByGender(gender);
        return memmerMapper.mapToMemberResponseDtoList(memberList);
    }
}
