package com.example.addressbook.exception;

public class AddressBookException extends RuntimeException{
    private String message;

    public AddressBookException(String message) {
        super(message);
        this.message =message;
    }
}
