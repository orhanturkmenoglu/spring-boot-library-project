package com.example.springbootlibraryproject.service;

import com.example.springbootlibraryproject.dto.response.ContactResponseDto;
import com.example.springbootlibraryproject.entity.Contact;
import com.example.springbootlibraryproject.mapper.ContactMapper;
import com.example.springbootlibraryproject.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ContactService {

    private final ContactRepository contactRepository;

    private final ContactMapper contactMapper;

    public List<ContactResponseDto> getContactsAll() {
        List<Contact> contactList = contactRepository.findAll();
        return contactMapper.mapToContactResponseDtoList(contactList);
    }

}
