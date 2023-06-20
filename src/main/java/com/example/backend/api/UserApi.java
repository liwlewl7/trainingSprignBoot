package com.example.backend.api;

import com.example.backend.Entity.User;
import com.example.backend.business.UserBusiness;
import com.example.backend.exception.BaseException;
import com.example.backend.exception.UserException;
import com.example.backend.model.MLoginRequest;
import com.example.backend.model.MRegisterRequest;
import com.example.backend.model.MRegisterResponse;
import com.example.backend.model.TestRestponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserApi {

    private  final UserBusiness business;
    public UserApi(UserBusiness business) {
        this.business = business;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MLoginRequest request) throws BaseException {
        String response = business.login(request);
        return ResponseEntity.ok(response);
    }

    @RequestMapping("register")
    @PostMapping
    public ResponseEntity<MRegisterResponse> registrt(@RequestBody MRegisterRequest request) throws BaseException {
        MRegisterResponse response = business.register(request);
        return ResponseEntity.ok(response);
    }
}
