package com.example.addressbook.service;

import com.example.addressbook.dto.AddressDTO;
import com.example.addressbook.exception.AddressBookException;
import com.example.addressbook.model.AddressEntity;
import com.example.addressbook.repository.AddressRepo;
import com.example.addressbook.util.MailSenderService;
import com.example.addressbook.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService implements IAddress{

    //make it loose coupling
    //created object
    @Autowired
    AddressRepo repository;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    MailSenderService sendMail;



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


    //add person details and send mail to person
    public String create1(AddressDTO dtoEntity){
        AddressEntity entity = new AddressEntity(dtoEntity);
        repository.save(entity);
        String token = tokenUtil.createToken(entity.getId());
        sendMail.sendMail(entity.getEmail(),"Details Added ", "Details are added to Address_Book \nHello "+entity.getName()+"\n\n You can see your details by click on the below link\n"+ "\n http://localhost:8888/home/retrieve/"+token);
        return token;
    }


    //update user details by id n generate token
    public String update1(AddressDTO dto,int id,String token){

        int userId = tokenUtil.decodeToken(token);
        if(userId == id){
            AddressEntity entity = repository.findById(id).get();
            entity.setName(dto.getName());
            entity.setAddress(dto.getAddress());
            entity.setCity(dto.getCity());
            entity.setState(dto.getState());
            entity.setEmail(dto.getEmail());
            entity.setPhoneNumber(dto.getPhoneNumber());
            entity.setZipcode(dto.getZipcode());

            repository.save(entity);
            sendMail.sendMail(entity.getEmail(),"Update mail...","Your details are Updated \n"+"Hello "+entity.getName()+ "\n\n You can see your details by click on the below link\n"+"\n http://localhost:8888/home/retrieve/"+token);
            return token;
        }else
            throw new AddressBookException("No user found with this id...");
    }


    //get the user by token
    public AddressEntity retrieve(String token){
        int id = tokenUtil.decodeToken(token);
        AddressEntity user = repository.findById(id).get();
        return user;
    }


    //get token by id
    public String userById(int id){
        AddressEntity user = repository.findById(id).get();
        String token = tokenUtil.createToken(user.getId());
        return token;
    }

    //delete user by token
    public String deleteUser(int id,String token) throws AddressBookException{
        int user_id= tokenUtil.decodeToken(token);
        if(user_id==id) {

            AddressEntity entity=repository.findById(id).get();
            sendMail.sendMail(entity.getEmail()," Removed You ","Your details are Removed from Address_Book \n Hello "+entity.getName()+"\n http://localhost:8888/home/retrieve/"+token);
            repository.deleteById(id);
            return "delete user "+id;
        }
        else
            return "incorrect user details";
    }
}
