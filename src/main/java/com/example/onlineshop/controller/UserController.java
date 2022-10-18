package com.example.onlineshop.controller;

import com.example.onlineshop.dto.request.AuthenticationRequest;
import com.example.onlineshop.dto.request.UserRegistrationRequest;
import com.example.onlineshop.dto.response.AuthenticationResponse;
import com.example.onlineshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody final UserRegistrationRequest userRegistrationRequest) {
        return ResponseEntity.ok(userService.register(userRegistrationRequest));

    }
}
