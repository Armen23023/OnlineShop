package com.example.onlineshop.service;

import com.example.onlineshop.dto.request.UserRegistrationRequest;
import com.example.onlineshop.dto.response.AuthenticationResponse;

public interface UserService {
    AuthenticationResponse register(UserRegistrationRequest user);
    AuthenticationResponse login(String email, String password);

}
