package com.example.springbootlibraryproject.repository;

import com.example.springbootlibraryproject.entity.Member;
import com.example.springbootlibraryproject.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findMemberByFirstName(String firstName);

    List<Member> findByGender(Gender gender);
}