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
    public ResponseEntity<ResponseDTO> getBuId(@PathVariable int id){
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
}
