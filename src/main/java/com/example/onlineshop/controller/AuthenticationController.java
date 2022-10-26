package com.example.onlineshop.controller;

import com.example.onlineshop.dto.request.AuthenticationRequest;
import com.example.onlineshop.dto.response.AuthenticationResponse;
import com.example.onlineshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class AuthenticationController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid final AuthenticationRequest authRequest) {
        return ResponseEntity.ok(userService.login(authRequest.getEmail(), authRequest.getPassword()));

    }
}
