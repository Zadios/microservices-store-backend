package com.viscovich.auth_service.service;

import com.viscovich.auth_service.dto.AuthDto;
import com.viscovich.auth_service.dto.TokenDto;
import com.viscovich.auth_service.model.AuthUser;
import com.viscovich.auth_service.repository.AuthRepository;
import com.viscovich.auth_service.config.JwtProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final JwtProvider jwtProvider;
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthRepository authRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider){
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    public AuthUser save(AuthDto dto){
        String passwordHash = passwordEncoder.encode(dto.getPassword());

        AuthUser user = AuthUser.builder().username(dto.getUsername()).password(passwordHash).role("USER").build();

        return authRepository.save(user);
    }

    public TokenDto login(AuthDto authDto){
        AuthUser user = authRepository.findByUsername(authDto.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no válido"));

        if(passwordEncoder.matches(authDto.getPassword(), user.getPassword())){
            return TokenDto.builder().token(jwtProvider.createToken(user)).build();
        } else {
            throw new RuntimeException("La contraseña es incorrecta");
        }
    }

    public Boolean validate(String token){
        return jwtProvider.validate(token);
    }
}
