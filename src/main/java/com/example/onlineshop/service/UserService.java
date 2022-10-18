package com.example.onlineshop.service;

import com.example.onlineshop.model.User;
import org.springframework.stereotype.Service;

public interface UserService {
    User register(User user);

    User findByEmail(String email);

    User findById(Long id);

}
