package com.example.springbootlibraryproject.service;

import com.example.springbootlibraryproject.dto.response.ContactResponseDto;
import com.example.springbootlibraryproject.dto.updateRequest.ContactUpdateRequestDto;
import com.example.springbootlibraryproject.entity.Contact;
import com.example.springbootlibraryproject.exceptions.ContactException;
import com.example.springbootlibraryproject.mapper.ContactMapper;
import com.example.springbootlibraryproject.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ContactService {

    private final ContactRepository contactRepository;

    private final ContactMapper contactMapper;

    @Cacheable(value = "contact")
    public List<ContactResponseDto> getContactsAll() {
        log.info("ContactService::getContactsAll started");

        List<Contact> contactList = contactRepository.findAll();

        log.info("ContactService::getContactsAll finished");
        return contactMapper.mapToContactResponseDtoList(contactList);
    }

    @CachePut(value = "contact",key = "#contactUpdateRequestDto")
    public ContactResponseDto updateBook(ContactUpdateRequestDto contactUpdateRequestDto) {
        log.info("ContactService::updateBook started");

        Optional<Contact> optionalBook = contactRepository.findById(contactUpdateRequestDto.getId());
        optionalBook.orElseThrow(() -> new ContactException("Contact not found id : " + contactUpdateRequestDto.getId()));

        Contact contact = contactMapper.mapToContact(contactUpdateRequestDto);
        Contact save = contactRepository.save(contact);

        log.info("ContactService::updateBook finished");
        return contactMapper.mapToContactResponseDto(save);
    }

    @CacheEvict(value = "contact",key = "#id")
    public void deleteContact(long id) {
        log.info("ContactService::deleteContact started");

        Optional<Contact> optionalContact = contactRepository.findById(id);
        optionalContact.orElseThrow(() -> new ContactException("Contact not found id : " + id));

        contactRepository.deleteById(id);
        log.info("ContactService::deleteContact finished");
    }
}
