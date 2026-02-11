package com.viscovich.auth_service.controller;
import com.viscovich.auth_service.dto.AuthDto;
import com.viscovich.auth_service.model.AuthUser;
import com.viscovich.auth_service.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public AuthUser register(@RequestBody AuthDto dto){
        return authService.save(dto);
    }
}
