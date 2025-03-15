package com.crm.crm.controller;

import com.crm.crm.model.Contact;
import com.crm.crm.repository.ContactRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ContactController {
    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/contacts")
    public List<Contact> contacts(){
        return (List<Contact>) contactRepository.findAll();
    }

    @PostMapping("/contacts")
    public ResponseEntity<Contact> createContact(@Valid @RequestBody Contact contact) throws URISyntaxException{
        Contact result = contactRepository.save(contact);
        return ResponseEntity.ok().body(result);
    }
}
