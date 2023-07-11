package com.example.addressbook.dto;

import com.example.addressbook.exception.AddressBookException;
import com.example.addressbook.model.AddressEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ResponseDTO {
    private String message;
    private Object object;
    private List list;


    //constructor having object and message
    public ResponseDTO(AddressEntity newPerson, String message) {
        this.object = newPerson;
        this.message =message;
    }


    //constructor having list and message
    public ResponseDTO(List<AddressEntity>persons, String message) {
        this.list = persons;
        this.message = message;
    }


    //constructor having message and message
    public ResponseDTO(String message1, String message2) {
        this.message=message2;
        this.object = message1;
    }


    //constructor having message and list
    public ResponseDTO(String message, List<String> message1) {
        this.message=message;
        this.object =message1;
    }


    //constructor having exception and message
    public ResponseDTO(AddressBookException exception, String message) {
        this.object = exception;
        this.message=message;
    }
}
