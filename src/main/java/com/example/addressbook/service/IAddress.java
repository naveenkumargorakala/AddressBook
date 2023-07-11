package com.example.addressbook.service;

import com.example.addressbook.dto.AddressDTO;
import com.example.addressbook.model.AddressEntity;

import java.util.List;

public interface IAddress {

    //Create person details
    AddressEntity create(AddressDTO dtoEntity);


    //update person details by using id
    AddressEntity update(AddressDTO dto ,int id);


    //get the person details by id
    AddressEntity getPeron(int id);


    //get all person details
    List<AddressEntity> getAllPersons();


    //delete person details by id
    String deletePerson(int id);


    //get the person details by email
    AddressEntity getByEmail(String email);
}
