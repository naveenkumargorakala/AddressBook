package com.example.addressbook.service;

import com.example.addressbook.dto.AddressDTO;
import com.example.addressbook.exception.AddressBookException;
import com.example.addressbook.model.AddressEntity;
import com.example.addressbook.repository.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService implements IAddress{

    //make it loose coupling
    @Autowired
    AddressRepo repository;



    //creating person details
    public AddressEntity create(AddressDTO dtoEntity) {
        AddressEntity newPerson = new AddressEntity(dtoEntity);
        return repository.save(newPerson);
    }


    //update person details
    public AddressEntity update(AddressDTO dto ,int id) {
        boolean isPerson = repository.existsById(id);
        if(isPerson){
            AddressEntity person =repository.findById(id).get();
            person.setName(dto.getName());
            person.setAddress(dto.getAddress());
            person.setCity(dto.getCity());
            person.setState(dto.getState());
            person.setEmail(dto.getEmail());
            person.setPhoneNumber(dto.getPhoneNumber());
            person.setZipcode(dto.getZipcode());

            return repository.save(person);
        }
        else
            throw new AddressBookException(id+" is Not available to update");

    }


    //get the person details by person_id
    public AddressEntity getPeron(int id) {
        boolean isPerson = repository.existsById(id);
        if(isPerson) {
            AddressEntity person = repository.findById(id).get();
            return  person;
        }
        else
            throw new AddressBookException(id+" is Not available to show");

    }


    //get all persons details
    public List<AddressEntity> getAllPersons() {
        List<AddressEntity> all = repository.findAll();
        return all;
    }


    //delete person details by id
    public String deletePerson(int id) {
        boolean isPerson = repository.existsById(id);
        if(isPerson) {
            repository.deleteById(id);
            return "person details deleted";
        }
        else
            throw new AddressBookException(id+" id is Not available to delete");

    }


    //get the person details by email
    public AddressEntity getByEmail(String email) {
        AddressEntity person = repository.findByEmail(email);
        return person;
    }
}
