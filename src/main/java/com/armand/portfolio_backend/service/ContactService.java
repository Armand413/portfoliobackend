package com.armand.portfolio_backend.service;

import com.armand.portfolio_backend.model.Contact;

import java.util.List;

public interface ContactService {

    List<Contact> getAllContacts();

    Contact getContactById(Long id);

    Contact submitContact(Contact contact);

    Contact markAsRead(Long id);

    void deleteContact(Long id);
}