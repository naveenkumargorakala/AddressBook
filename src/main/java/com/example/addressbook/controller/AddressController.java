package com.example.addressbook.controller;

import com.example.addressbook.dto.AddressDTO;
import com.example.addressbook.dto.ResponseDTO;
import com.example.addressbook.model.AddressEntity;
import com.example.addressbook.service.IAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/home")
public class AddressController {

    // Check Whether the IOC Container have the Object or not
    //created object
    @Autowired
    IAddress addressService;

    //Add the Person Details
    @PostMapping("/addperson")
    public ResponseEntity<ResponseDTO> addPerson(@Valid @RequestBody AddressDTO dtoEntity){
        AddressEntity newPerson = addressService.create(dtoEntity);
        ResponseDTO responseDTO = new ResponseDTO(newPerson,"Person details Created...");
        log.info("adding Person details");
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);

    }


    //Update the person Details
    @PutMapping("/updateperson/{id}")
    public ResponseEntity<ResponseDTO> update(@Valid @RequestBody AddressDTO dto,@PathVariable int id){
        AddressEntity updatePerson = addressService.update(dto,id);
        ResponseDTO responseDTO = new ResponseDTO(updatePerson,"Updates Person details...");
        log.info("update the person details",responseDTO);
        return new ResponseEntity<>(responseDTO,HttpStatus.ACCEPTED);
    }

    //get the person details by id
    @GetMapping("/getbyid/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable int id){
        AddressEntity person = addressService.getPeron(id);
        ResponseDTO responseDTO = new ResponseDTO(person,id+" person details...");
        log.info(id+"details are shown");
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    //get all persons details
    @GetMapping("/getall")
    public ResponseEntity<List<ResponseDTO>> getAll(){
        List<AddressEntity> persons= addressService.getAllPersons();
        ResponseDTO responseDTO = new ResponseDTO(persons,"all persons...");
        log.info("all person details ");
        return new ResponseEntity<>(responseDTO.getList(),HttpStatus.OK);
    }


    //delete person details by id
    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable int id){
        String person = addressService.deletePerson(id);
        ResponseDTO responseDTO = new ResponseDTO(person,"Deleted the person details...");
        log.info(id+" person details are deleted ");
        return new ResponseEntity<String>(String.valueOf(responseDTO),HttpStatus.OK);
    }

    //get the person details by email
    @GetMapping("/personbyemail/{email}")
    public ResponseEntity<ResponseDTO> getByEmail(@PathVariable String email){
        AddressEntity person = addressService.getByEmail(email);
        ResponseDTO responseDTO = new ResponseDTO(person,"person details by email...");
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }


    //add user details and generate token
    @PostMapping("/adduser")
    public ResponseEntity<ResponseDTO> addPerson1(@Valid @RequestBody AddressDTO dtoEntity){
        String newPerson = addressService.create1(dtoEntity);
        ResponseDTO responseDTO = new ResponseDTO(newPerson,"Person details Created...");
        log.info("adding Person details");
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }


    //update user by id and generate token
    @PutMapping("/updateuser/{id}/{token}")
    public ResponseEntity<ResponseDTO> update1(@RequestBody AddressDTO dto,@PathVariable int id,@PathVariable String token){
        String entity = addressService.update1(dto,id,token);
        ResponseDTO responseDTO =new ResponseDTO(entity,"person details updated...");
        return new ResponseEntity<>(responseDTO,HttpStatus.ACCEPTED);
    }


    //get the details of user by id
    @GetMapping("/retrieve/{token}")
    public ResponseEntity<ResponseDTO> retrieve(@PathVariable String token){
        AddressEntity user = addressService.retrieve(token);
        ResponseDTO responseDTO =new ResponseDTO(user,"user detailsss...");
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    //generate token by id
    @GetMapping("/generatetokenbyid/{id}")
    public ResponseEntity<ResponseDTO> userById(@PathVariable int id){
        String user = addressService.userById(id);
        ResponseDTO responseDTO = new ResponseDTO(user,id+" details...");
        return new ResponseEntity<>(responseDTO,HttpStatus.FOUND);
    }

    //delete User by Id
    @DeleteMapping("deleteuser/{id}/{token}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable int id,@PathVariable String token){
        String message = addressService.deleteUser(id,token);
        ResponseDTO responseDTO = new ResponseDTO(message," user id:"+ id);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
}
