package com.example.addressbook.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressDTO {


    @Pattern(regexp = "^[A-Z][a-z]{2,}$",message = "You are Entered Wrong Format")
    private String name;
    private String address;
    private String city;
    private String state;
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",message = "You are Entered Wrong Format of Email")
    private String email;
    private long phoneNumber;
    private int zipcode;
}
