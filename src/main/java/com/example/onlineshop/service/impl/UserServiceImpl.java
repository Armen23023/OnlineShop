package com.example.onlineshop.service.impl;

import com.example.onlineshop.dto.request.UserRegistrationRequest;
import com.example.onlineshop.dto.response.AuthenticationResponse;
import com.example.onlineshop.exceptions.BadRequestException;
import com.example.onlineshop.exceptions.ResourceNotFoundException;
import com.example.onlineshop.mappers.UserRegistrationRequestToUserMapper;
import com.example.onlineshop.model.User;
import com.example.onlineshop.repository.RoleRepository;
import com.example.onlineshop.repository.UserRepository;
import com.example.onlineshop.security.jwt.JwtTokenProvider;
import com.example.onlineshop.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRegistrationRequestToUserMapper userRegRequestMapper;



    @Override
    @Transactional
    public AuthenticationResponse register(final UserRegistrationRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isEmpty()) {

            User user = userRegRequestMapper.apply(request);
            user.setRoles(Collections.singletonList(roleRepository.findByName("USER")));
            User registeredUser = userRepository.save(user);
            log.info("In register - user: {} successfully registered ", registeredUser);

            return AuthenticationResponse.builder()
                    .success(true)
                    .token(jwtTokenProvider.createToken(user.getEmail(), user.getRoles()))
                    .message("Successfully registered")
                    .build();
        } else {
            throw new BadRequestException("Email already used");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public AuthenticationResponse login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return AuthenticationResponse.builder()
                    .message("Ok")
                    .success(true)
                    .token(jwtTokenProvider.createToken(email, user.get().getRoles()))
                    .build();
        } else {
            throw new ResourceNotFoundException("Invalid email or password");
        }
    }
}
