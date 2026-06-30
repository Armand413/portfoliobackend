package com.armand.portfolio_backend.controller;

import com.armand.portfolio_backend.dto.request.ContactRequestDTO;
import com.armand.portfolio_backend.dto.response.ContactResponseDTO;
import com.armand.portfolio_backend.mapper.ContactMapper;
import com.armand.portfolio_backend.model.Contact;
import com.armand.portfolio_backend.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private final ContactService contactService;
    private final ContactMapper contactMapper;

    public ContactController(ContactService contactService, ContactMapper contactMapper) {
        this.contactService = contactService;
        this.contactMapper = contactMapper;
    }

    @PostMapping
    public ResponseEntity<ContactResponseDTO> submitContact(@Valid @RequestBody ContactRequestDTO requestDTO) {
        Contact contact = contactMapper.toEntity(requestDTO);
        Contact created = contactService.submitContact(contact);
        return ResponseEntity.status(HttpStatus.CREATED).body(contactMapper.toDTO(created));
    }

    @GetMapping
    public ResponseEntity<List<ContactResponseDTO>> getAllContacts() {
        List<ContactResponseDTO> result = contactService.getAllContacts().stream()
                .map(contactMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactResponseDTO> getContactById(@PathVariable Long id) {
        return ResponseEntity.ok(contactMapper.toDTO(contactService.getContactById(id)));
    }

    @PatchMapping("/{id}/read")
    public ResponseEntity<ContactResponseDTO> markAsRead(@PathVariable Long id) {
        return ResponseEntity.ok(contactMapper.toDTO(contactService.markAsRead(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }
}