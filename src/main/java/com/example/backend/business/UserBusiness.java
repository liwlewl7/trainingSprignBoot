package com.example.backend.business;

import com.example.backend.Entity.User;
import com.example.backend.exception.BaseException;
import com.example.backend.exception.UserException;
import com.example.backend.mapper.UserMapper;
import com.example.backend.model.MLoginRequest;
import com.example.backend.model.MRegisterRequest;
import com.example.backend.model.MRegisterResponse;
import com.example.backend.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserBusiness {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserBusiness(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public String login(MLoginRequest request) throws BaseException {
        // validate request

        // validate database
        Optional<User> opt = userService.findByEmail(request.getEmail());
        if(opt.isEmpty()){
            throw UserException.loginfailEmailNotFound();
        }
        User user = opt.get();
        if(!userService.matchPassword(request.getPassword(),user.getPassword())){
            throw UserException.loginfailPasswordIncorrect();
        }
        // TODO:generate JWT
        String token = "JWT";
        return token;
    }

    public MRegisterResponse register(MRegisterRequest request) throws BaseException {
        User user = userService.create(request.getEmail(), request.getPassword(), request.getName());

        return userMapper.toRegisterResponse(user);
    }
}
