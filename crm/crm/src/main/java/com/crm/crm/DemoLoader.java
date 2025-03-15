package com.crm.crm;

import com.crm.crm.model.Contact;
import com.crm.crm.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DemoLoader implements CommandLineRunner {
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public void run(String... args) throws Exception {
        this.contactRepository.save(new Contact("Laxman", "Lal", "laxman@gmail.com"));
    }
}
