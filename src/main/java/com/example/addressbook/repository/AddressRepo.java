package com.example.addressbook.repository;

import com.example.addressbook.model.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends JpaRepository<AddressEntity,Integer> {


    //get the person details by email
    @Query(value = "select * from address_book where email=:email",nativeQuery = true)
    AddressEntity findByEmail(String email);
}
