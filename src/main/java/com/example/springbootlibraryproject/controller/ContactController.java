package com.example.springbootlibraryproject.controller;

import com.example.springbootlibraryproject.constants.ContactMessage;
import com.example.springbootlibraryproject.dto.response.ContactResponseDto;
import com.example.springbootlibraryproject.dto.updateRequest.ContactUpdateRequestDto;
import com.example.springbootlibraryproject.exceptions.SuccessResponse;
import com.example.springbootlibraryproject.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @GetMapping ("/contactsAll")
    public ResponseEntity<List<ContactResponseDto>> getContactsAll() {
        List<ContactResponseDto> contactResponseDtoList = contactService.getContactsAll();
        return SuccessResponse.responseBuilder(ContactMessage.GET_CONTACTS_ALL, HttpStatus.OK, contactResponseDtoList);
    }

    @PutMapping("/updateContact")
    public ResponseEntity<ContactResponseDto> updateContact(@Valid @RequestBody ContactUpdateRequestDto contactUpdateRequestDto) {
        ContactResponseDto contactResponseDto = contactService.updateBook(contactUpdateRequestDto);
        return SuccessResponse.responseBuilder(ContactMessage.UPDATE_CONTACT, HttpStatus.CREATED, contactResponseDto);
    }

    @DeleteMapping("deleteContact/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable("id") long id) {
        contactService.deleteContact(id);
        return SuccessResponse.responseBuilder(ContactMessage.DELETE_CONTACT_BY_ID, HttpStatus.OK);
    }
}
