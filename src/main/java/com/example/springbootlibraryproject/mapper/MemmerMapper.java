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
                .contact(Contact.builder()
                        .id(memberUpdateRequestDto.getContactUpdateRequestDto().getId())
                        .address(memberUpdateRequestDto.getContactUpdateRequestDto().getAddress())
                        .city(memberUpdateRequestDto.getContactUpdateRequestDto().getCity())
                        .district(memberUpdateRequestDto.getContactUpdateRequestDto().getDistrict())
                        .email(memberUpdateRequestDto.getContactUpdateRequestDto().getEmail())
                        .phoneNumber(memberUpdateRequestDto.getContactUpdateRequestDto().getPhoneNumber())
                        .build())
                .build();
    }

    public Member mapToMember(MemberRequestDto memberRequestDto) {
        return Member.builder()
                .firstName(memberRequestDto.getFirstName())
                .lastName(memberRequestDto.getLastName())
                .identityNo(memberRequestDto.getIdentityNo())
                .gender(memberRequestDto.getGender())
                .contact(Contact.builder()
                        .address(memberRequestDto.getContact().getAddress())
                        .city(memberRequestDto.getContact().getCity())
                        .district(memberRequestDto.getContact().getDistrict())
                        .email(memberRequestDto.getContact().getEmail())
                        .phoneNumber(memberRequestDto.getContact().getPhoneNumber())
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
                .contact(ContactResponseDto.builder()
                        .id(member.getContact().getId())
                        .address(member.getContact().getAddress())
                        .city(member.getContact().getCity())
                        .district(member.getContact().getDistrict())
                        .email(member.getContact().getEmail())
                        .phoneNumber(member.getContact().getPhoneNumber())
                        .build())
                .build();
    }
}
