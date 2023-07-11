package com.example.addressbook.model;

import com.example.addressbook.dto.AddressDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="address_book")
public class AddressEntity {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String email;
    private long phoneNumber;
    private int zipcode;


    //custom constructor for adding person
    public AddressEntity(AddressDTO dtoEntity) {
        this.name = dtoEntity.getName();
        this.address=dtoEntity.getAddress();
        this.city =dtoEntity.getCity();
        this.state = dtoEntity.getState();
        this.phoneNumber = dtoEntity.getPhoneNumber();
        this.email = dtoEntity.getEmail();
        this.zipcode=dtoEntity.getZipcode();
    }
}
