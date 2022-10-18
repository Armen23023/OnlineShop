package com.example.onlineshop.mappers;

import com.example.onlineshop.dto.request.UserRegistrationRequest;
import com.example.onlineshop.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.function.Function;



@Component
@RequiredArgsConstructor
public class UserRegistrationRequestToUserMapper implements Function<UserRegistrationRequest, User> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public User apply(final UserRegistrationRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return user;

    }
}
