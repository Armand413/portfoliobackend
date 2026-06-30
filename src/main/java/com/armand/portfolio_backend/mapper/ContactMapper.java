package com.armand.portfolio_backend.mapper;

import com.armand.portfolio_backend.dto.request.ContactRequestDTO;
import com.armand.portfolio_backend.dto.response.ContactResponseDTO;
import com.armand.portfolio_backend.model.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {

    public ContactResponseDTO toDTO(Contact contact) {
        ContactResponseDTO dto = new ContactResponseDTO();
        dto.setId(contact.getId());
        dto.setName(contact.getName());
        dto.setEmail(contact.getEmail());
        dto.setMessage(contact.getMessage());
        dto.setCreatedAt(contact.getCreatedAt());
        dto.setRead(contact.isRead());
        return dto;
    }

    public Contact toEntity(ContactRequestDTO dto) {
        Contact contact = new Contact();
        contact.setName(dto.getName());
        contact.setEmail(dto.getEmail());
        contact.setMessage(dto.getMessage());
        return contact;
    }
}