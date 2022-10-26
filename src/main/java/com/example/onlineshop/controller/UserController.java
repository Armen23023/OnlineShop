package com.example.onlineshop.controller;

import com.example.onlineshop.dto.request.AuthenticationRequest;
import com.example.onlineshop.dto.request.UserRegistrationRequest;
import com.example.onlineshop.dto.response.AppResponseInfo;
import com.example.onlineshop.dto.response.AuthenticationResponse;
import com.example.onlineshop.dto.response.ProductResponse;
import com.example.onlineshop.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value = "Register user",
            notes = "Provide api to register new user",
            response = AuthenticationResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = AuthenticationResponse.class),
            @ApiResponse(code = 400, message = "Bad request", response = AppResponseInfo.class)
    })
     public ResponseEntity<AuthenticationResponse> register(@RequestBody final UserRegistrationRequest userRegistrationRequest) {
        return ResponseEntity.ok(userService.register(userRegistrationRequest));

    }
}
