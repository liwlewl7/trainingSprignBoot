package com.example.backend.service;

import com.example.backend.Entity.User;
import com.example.backend.exception.BaseException;
import com.example.backend.exception.UserException;
import com.example.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public User update(User user) {
        return repository.save(user);
    }
    //Update
    public User updateName(String id, String name) throws BaseException {
        Optional<User> opt = repository.findById(id);
        if (opt.isEmpty()) {
            throw UserException.updateNameNotfound();
        }
        User user = opt.get();
        user.setName(name);

        return repository.save(user);
    }
    // Delete
    public void deleteById(String id){
        repository.deleteById(id);
    }

    //EndcodePassword
    public boolean matchPassword(String rawPassword, String endodedPassword) {
        return passwordEncoder.matches(rawPassword, endodedPassword);
    }

    public User create(String email, String password, String name) throws BaseException {
        //validate
        if (Objects.isNull(email)) {
            throw UserException.createEmailNull();
        }
        if (Objects.isNull(password)) {
            throw UserException.createPasswordNull();
        }
        if (Objects.isNull(name)) {
            throw UserException.createNameNull();
        }

        //varify
        if (repository.existsByEmail(email)) {
            throw UserException.createEmailDuplicated();
        }

        //save
        User entity = new User();
        entity.setEmail(email);
        entity.setPassword(passwordEncoder.encode(password));
        entity.setName(name);

        return repository.save(entity);
    }
}
