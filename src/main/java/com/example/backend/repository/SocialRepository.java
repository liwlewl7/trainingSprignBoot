package com.example.backend.repository;

import com.example.backend.Entity.Social;
import com.example.backend.Entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SocialRepository extends CrudRepository<Social,String> {

    Optional<Social> findByUser(User user);

}
