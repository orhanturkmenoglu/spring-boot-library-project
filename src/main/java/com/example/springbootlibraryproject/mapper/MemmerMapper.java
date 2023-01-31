package com.example.springbootlibraryproject.mapper;

import com.example.springbootlibraryproject.dto.request.MemberRequestDto;
import com.example.springbootlibraryproject.dto.response.ContactResponseDto;
import com.example.springbootlibraryproject.dto.response.MemberResponseDto;
import com.example.springbootlibraryproject.dto.updateRequest.MemberUpdateRequestDto;
import com.example.springbootlibraryproject.entity.Contact;
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

    public Member mapToMember(MemberUpdateRequestDto memberUpdateRequestDto) {
        return Member.builder()
                .id(memberUpdateRequestDto.getId())
                .firstName(memberUpdateRequestDto.getFirstName())
                .lastName(memberUpdateRequestDto.getLastName())
                .identityNo(memberUpdateRequestDto.getIdentityNo())
                .gender(memberUpdateRequestDto.getGender())
                .phoneNumber(memberUpdateRequestDto.getPhoneNumber())
                .email(memberUpdateRequestDto.getEmail())
                .contact(Contact.builder()
                        .id(memberUpdateRequestDto.getContactUpdateRequestDto().getId())
                        .city(memberUpdateRequestDto.getContactUpdateRequestDto().getCity())
                        .district(memberUpdateRequestDto.getContactUpdateRequestDto().getDistrict())
                        .address(memberUpdateRequestDto.getContactUpdateRequestDto().getAddress())
                        .build())
                .build();
    }

    public Member mapToMember(MemberRequestDto memberRequestDto) {
        return Member.builder()
                .firstName(memberRequestDto.getFirstName())
                .lastName(memberRequestDto.getLastName())
                .identityNo(memberRequestDto.getIdentityNo())
                .gender(memberRequestDto.getGender())
                .phoneNumber(memberRequestDto.getPhoneNumber())
                .email(memberRequestDto.getEmail())
                .contact(Contact.builder()
                        .city(memberRequestDto.getContact().getCity())
                        .address(memberRequestDto.getContact().getAddress())
                        .district(memberRequestDto.getContact().getDistrict())
                        .build())
                .build();
    }

    public MemberResponseDto mapToMemberResponseDto(Member member) {
        return MemberResponseDto.builder()
                .id(member.getId())
                .firstName(member.getFirstName())
                .lastName(member.getLastName())
                .identityNo(member.getIdentityNo())
                .gender(member.getGender())
                .phoneNumber(member.getPhoneNumber())
                .email(member.getEmail())
                .contact(ContactResponseDto.builder()
                        .id(member.getContact().getId())
                        .city(member.getContact().getCity())
                        .district(member.getContact().getDistrict())
                        .address(member.getContact().getAddress())
                        .build())
                .build();
    }
}
