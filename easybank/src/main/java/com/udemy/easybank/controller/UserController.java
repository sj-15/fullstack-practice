package com.udemy.easybank.controller;

import com.udemy.easybank.model.Customer;
import com.udemy.easybank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer){
        try {
            String hashPwd = encoder.encode(customer.getPwd());
            customer.setPwd(hashPwd);
            Customer savedCustomer = customerRepository.save(customer);
            if(savedCustomer.getId() > 0){
                return ResponseEntity.status(HttpStatus.CREATED).body("Given user registered successfully!");
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User registration failed");
            }
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An exception occurred: " + ex.getMessage());
        }
    }
}
