package com.example.springbootlibraryproject.mapper;

import com.example.springbootlibraryproject.dto.request.MemberRequestDto;
import com.example.springbootlibraryproject.dto.response.MemberResponseDto;
import com.example.springbootlibraryproject.entity.Member;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemmerMapper {

    public List<MemberResponseDto> mapToMemberResponseDtoList(List<Member> memberList) {
        return memberList
                .stream()
                .map(this::mapToMemberResponseDto)
                .collect(Collectors.toList());
    }

    public Member mapToMember(MemberRequestDto memberRequestDto) {
        return Member.builder()
                .firstName(memberRequestDto.getFirstName())
                .lastName(memberRequestDto.getLastName())
                .identityNo(memberRequestDto.getIdentityNo())
                .gender(memberRequestDto.getGender())
                .contact(memberRequestDto.getContact())
                .build();
    }

    public MemberResponseDto mapToMemberResponseDto(Member member) {
        return MemberResponseDto.builder()
                .id(member.getId())
                .firstName(member.getFirstName())
                .lastName(member.getLastName())
                .identityNo(member.getIdentityNo())
                .gender(member.getGender())
                .contact(member.getContact())
                .build();
    }
}
