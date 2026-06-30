package com.armand.portfolio_backend.service.impl;

import com.armand.portfolio_backend.exception.ResourceNotFoundException;
import com.armand.portfolio_backend.model.Contact;
import com.armand.portfolio_backend.repository.ContactRepository;
import com.armand.portfolio_backend.service.ContactService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public Contact getContactById(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Message introuvable avec l'id : " + id));
    }

    @Override
    public Contact submitContact(Contact contact) {
        contact.setRead(false); // sécurité : un visiteur ne peut jamais soumettre un message déjà "lu"
        return contactRepository.save(contact);
    }

    @Override
    public Contact markAsRead(Long id) {
        Contact existing = getContactById(id);
        existing.setRead(true);
        return contactRepository.save(existing);
    }

    @Override
    public void deleteContact(Long id) {
        Contact existing = getContactById(id);
        contactRepository.delete(existing);
    }
}