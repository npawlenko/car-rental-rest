package com.example.carrental.web;

import com.example.carrental.model.dto.client.LoginRequestDto;
import com.example.carrental.model.dto.client.RegisterRequestDto;
import com.example.carrental.model.dto.client.TokenResponseDto;
import com.example.carrental.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthResource {
    private final ClientService clientService;

    @PostMapping("/register")
    public TokenResponseDto register(@RequestBody RegisterRequestDto registerRequestDTO) {
        return clientService.register(registerRequestDTO);
    }

    @PostMapping("/login")
    public TokenResponseDto login(@RequestBody LoginRequestDto loginRequestDTO) {
        return clientService.login(loginRequestDTO);
    }
}
