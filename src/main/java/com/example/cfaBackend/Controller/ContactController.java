package com.example.cfaBackend.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cfaBackend.Entity.Contact;
import com.example.cfaBackend.Service.ContactService;

import java.util.HashMap;
import java.util.Map;

import java.util.List;


@RestController
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/contactanos")
    public ResponseEntity<Map<String, String>> submitContactForm(@RequestBody Contact contact) {
        contactService.saveContact(contact);
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "¡Formulario enviado exitosamente!");
        
        return ResponseEntity.ok(response); 
    }

    @GetMapping("/contactanos")
    public List<Contact> getAllContacts() {
    return contactService.getAllContacts();
}

}
