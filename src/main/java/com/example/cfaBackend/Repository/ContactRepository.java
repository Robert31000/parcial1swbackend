package com.example.cfaBackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cfaBackend.Entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
