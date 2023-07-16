package com.example.addressbook;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class AddressBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(AddressBookApplication.class, args);
        System.out.println("Address Book Application is Started...");
        log.info("Your Application is Start.....");
        log.warn("make sure you are created all the layers");
    }

}
