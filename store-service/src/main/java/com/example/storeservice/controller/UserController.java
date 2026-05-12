package com.example.storeservice.controller;

import com.example.storeservice.dto.LoginRequest;
import com.example.storeservice.dto.LoginResponse;
import com.example.storeservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = userService.login(request.getPhone());
        return ResponseEntity.ok(response);
    }
}