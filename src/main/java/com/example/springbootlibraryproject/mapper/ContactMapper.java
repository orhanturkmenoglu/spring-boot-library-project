package com.example.springbootlibraryproject.mapper;

import com.example.springbootlibraryproject.dto.request.ContactRequestDto;
import com.example.springbootlibraryproject.dto.response.ContactResponseDto;
import com.example.springbootlibraryproject.dto.updateRequest.ContactUpdateRequestDto;
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

    public Contact mapToContact(ContactRequestDto contactRequestDto) {
        return Contact.builder()
                .city(contactRequestDto.getCity())
                .district(contactRequestDto.getDistrict())
                .address(contactRequestDto.getAddress())
                .build();
    }

    public Contact mapToContact(ContactUpdateRequestDto contactUpdateRequestDto) {
        return Contact.builder()
                .id(contactUpdateRequestDto.getId())
                .city(contactUpdateRequestDto.getCity())
                .district(contactUpdateRequestDto.getDistrict())
                .address(contactUpdateRequestDto.getAddress())
                .build();
    }

    public ContactResponseDto mapToContactResponseDto(Contact contact) {
        return ContactResponseDto.builder()
                .id(contact.getId())
                .city(contact.getCity())
                .district(contact.getDistrict())
                .address(contact.getAddress())
                .build();
    }
}
