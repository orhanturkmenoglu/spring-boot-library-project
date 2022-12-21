package com.example.springbootlibraryproject.mapper;

import com.example.springbootlibraryproject.dto.response.ContactResponseDto;
import com.example.springbootlibraryproject.entity.Contact;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContactMapper {

    public List<ContactResponseDto> mapToContactResponseDtoList(List<Contact> contactList) {
        return contactList
                .stream()
                .map(this::mapToContactResponseDto)
                .collect(Collectors.toList());
    }

    public ContactResponseDto mapToContactResponseDto(Contact contact) {
        return ContactResponseDto.builder()
                .id(contact.getId())
                .city(contact.getCity())
                .district(contact.getDistrict())
                .address(contact.getAddress())
                .phoneNumber(contact.getPhoneNumber())
                .email(contact.getEmail())
                .member(contact.getMember())
                .build();
    }
}
