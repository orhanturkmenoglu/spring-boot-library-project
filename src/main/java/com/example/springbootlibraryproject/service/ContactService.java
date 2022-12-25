package com.example.springbootlibraryproject.service;

import com.example.springbootlibraryproject.dto.request.ContactRequestDto;
import com.example.springbootlibraryproject.dto.response.ContactResponseDto;
import com.example.springbootlibraryproject.dto.updateRequest.ContactUpdateRequestDto;
import com.example.springbootlibraryproject.entity.Contact;
import com.example.springbootlibraryproject.exceptions.ContactException;
import com.example.springbootlibraryproject.mapper.ContactMapper;
import com.example.springbootlibraryproject.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public ContactResponseDto createContact(ContactRequestDto contactRequestDto) {
        log.info("ContactService::createContact started");

        Contact contact = contactMapper.mapToContact(contactRequestDto);
        Contact save = contactRepository.save(contact);

        log.info("ContactService::createContact finished");
        return contactMapper.mapToContactResponseDto(save);
    }

    public List<ContactResponseDto> getContactsAll() {
        log.info("ContactService::getContactsAll started");

        List<Contact> contactList = contactRepository.findAll();

        log.info("ContactService::getContactsAll finished");
        return contactMapper.mapToContactResponseDtoList(contactList);
    }

    public ContactResponseDto updateBook(ContactUpdateRequestDto contactUpdateRequestDto) {
        log.info("ContactService::updateBook started");

        Optional<Contact> optionalBook = contactRepository.findById(contactUpdateRequestDto.getId());
        optionalBook.orElseThrow(() -> new ContactException("Contact not found id : " + contactUpdateRequestDto.getId()));

        Contact contact = contactMapper.mapToContact(contactUpdateRequestDto);
        Contact save = contactRepository.save(contact);

        log.info("ContactService::updateBook finished");
        return contactMapper.mapToContactResponseDto(save);
    }

    public void deleteBook(long id) {
        log.info("ContactService::deleteBook started");

        Optional<Contact> optionalContact = contactRepository.findById(id);
        optionalContact.orElseThrow(() -> new ContactException("Contact not found id : " + id));

        log.info("ContactService::deleteBook finished");
        contactRepository.deleteById(id);
    }


}
