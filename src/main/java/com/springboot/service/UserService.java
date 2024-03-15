package com.springboot.service;

import com.springboot.dto.RegistrationDto;
import com.springboot.entity.User;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    User findByEmail(String email);
}
