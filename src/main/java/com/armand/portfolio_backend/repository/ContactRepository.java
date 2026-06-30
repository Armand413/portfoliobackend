package com.armand.portfolio_backend.repository;

import com.armand.portfolio_backend.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findByReadFalse();

    List<Contact> findAllByOrderByCreatedAtDesc();
}