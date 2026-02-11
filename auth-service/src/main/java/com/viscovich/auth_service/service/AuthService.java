package com.viscovich.auth_service.service;

import com.viscovich.auth_service.dto.AuthDto;
import com.viscovich.auth_service.model.AuthUser;
import com.viscovich.auth_service.repository.AuthRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthRepository authRepository, PasswordEncoder passwordEncoder){
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthUser save(AuthDto dto){
        String passwordHash = passwordEncoder.encode(dto.getPassword());

        AuthUser user = AuthUser.builder().username(dto.getUsername()).password(passwordHash).role("USER").build();

        return authRepository.save(user);
    }
}
