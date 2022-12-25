package com.example.springbootlibraryproject.controller;

import com.example.springbootlibraryproject.constants.ContactMessage;
import com.example.springbootlibraryproject.dto.request.ContactRequestDto;
import com.example.springbootlibraryproject.dto.response.ContactResponseDto;
import com.example.springbootlibraryproject.dto.updateRequest.ContactUpdateRequestDto;
import com.example.springbootlibraryproject.exceptions.SuccessResponse;
import com.example.springbootlibraryproject.service.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
@Slf4j
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<ContactResponseDto> createContact(@Valid @RequestBody ContactRequestDto contactRequestDto) {
        ContactResponseDto contactResponseDto = contactService.createContact(contactRequestDto);
        return SuccessResponse.responseBuilder(ContactMessage.CREATE_CONTACT, HttpStatus.CREATED, contactResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<ContactResponseDto>> getContactsAll() {
        List<ContactResponseDto> contactResponseDtoList = contactService.getContactsAll();
        return SuccessResponse.responseBuilder(ContactMessage.GET_CONTACTS_ALL, HttpStatus.OK, contactResponseDtoList);
    }

    @PutMapping
    public ResponseEntity<ContactResponseDto> updateBook(@Valid @RequestBody ContactUpdateRequestDto contactUpdateRequestDto) {
        ContactResponseDto contactResponseDto = contactService.updateBook(contactUpdateRequestDto);
        return SuccessResponse.responseBuilder(ContactMessage.UPDATE_CONTACT, HttpStatus.CREATED, contactResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") long id) {
        contactService.deleteBook(id);
        return SuccessResponse.responseBuilder(ContactMessage.DELETE_CONTACT_BY_ID, HttpStatus.OK);
    }

}
