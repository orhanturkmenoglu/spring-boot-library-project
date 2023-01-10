package com.example.springbootlibraryproject.service;

import com.example.springbootlibraryproject.dto.request.MemberRequestDto;
import com.example.springbootlibraryproject.dto.response.MemberResponseDto;
import com.example.springbootlibraryproject.dto.updateRequest.MemberUpdateRequestDto;
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
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemmerMapper memmerMapper;

    public MemberResponseDto createMember(MemberRequestDto memberRequestDto) {
        log.info("MemberService::createMember started");

        Member member = memmerMapper.mapToMember(memberRequestDto);
        Member save = memberRepository.save(member);

        log.info("MemberService::createMember finished");
        return memmerMapper.mapToMemberResponseDto(save);
    }

    public List<MemberResponseDto> getMembersAll(String firstName) {
        log.info("MemberService::getMembersAll started");

        List<Member> memberList;

        if (StringUtils.isNotEmpty(firstName) || StringUtils.isNotBlank(firstName)) {
            memberList = memberRepository.findMemberByFirstName(firstName);
            return memmerMapper.mapToMemberResponseDtoList(memberList);
        }

        memberList = memberRepository.findAll();

        log.info("MemberService::getMembersAll finished");
        return memmerMapper.mapToMemberResponseDtoList(memberList);
    }


    public List<MemberResponseDto> getMembersByGender(Gender gender) {
        log.info("MemberService::getMembersByGender started");

        List<Member> memberList;
        if (Objects.isNull(gender)) {
            throw new MemberException("Member gender must not be null or empty");
        }

        memberList = memberRepository.findByGender(gender);

        log.info("MemberService::getMembersByGender finished");
        return memmerMapper.mapToMemberResponseDtoList(memberList);
    }

    public MemberResponseDto updateMember(MemberUpdateRequestDto memberUpdateRequestDto) {
        log.info("MemberService::updateMember started");

        Optional<Member> optionalMember = memberRepository.findById(memberUpdateRequestDto.getId());
        optionalMember.orElseThrow(() -> new MemberException("Member not found id : " + memberUpdateRequestDto.getId()));

        Member member = memmerMapper.mapToMember(memberUpdateRequestDto);
        Member save = memberRepository.save(member);

        log.info("MemberService::updateMember finished");
        return memmerMapper.mapToMemberResponseDto(save);
    }

    public void deleteMember(long id) {
        log.info("MemberService::deleteMember started");

        Optional<Member> optionalMember = memberRepository.findById(id);
        optionalMember.orElseThrow(() -> new MemberException("Member not found id : " + id));


        memberRepository.deleteById(id);
        log.info("BookService::deleteMember finished");
    }

}
