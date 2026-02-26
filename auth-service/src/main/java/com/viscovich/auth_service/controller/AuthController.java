package com.viscovich.auth_service.controller;
import com.viscovich.auth_service.dto.AuthDto;
import com.viscovich.auth_service.dto.TokenDto;
import com.viscovich.auth_service.model.AuthUser;
import com.viscovich.auth_service.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthUser register(@RequestBody AuthDto dto){
        return authService.save(dto);
    }

    @PostMapping("/login")
    public TokenDto login(@RequestBody AuthDto dto){
        return authService.login(dto);
    }

    @PostMapping("/validate")
    public Boolean validateToken(@RequestParam String token){
        return authService.validate(token);
    }
}
