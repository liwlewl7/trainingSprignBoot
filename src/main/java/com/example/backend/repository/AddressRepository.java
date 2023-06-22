package com.example.backend.repository;


import com.example.backend.Entity.Address;
import com.example.backend.Entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address,String> {
    List<Address> findByUser(User user);
}
