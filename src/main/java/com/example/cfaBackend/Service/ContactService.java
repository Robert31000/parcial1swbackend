package com.example.cfaBackend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cfaBackend.Entity.Contact;
import com.example.cfaBackend.Repository.ContactRepository;

import java.util.List; 


@Service
public class ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public void saveContact(Contact contact) {
        contactRepository.save(contact);
    }
     public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }
}