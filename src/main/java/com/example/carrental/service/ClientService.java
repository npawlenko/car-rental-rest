package com.example.carrental.service;

import com.example.carrental.domain.Role;
import com.example.carrental.domain.RoleEnum;
import com.example.carrental.domain.User;
import com.example.carrental.exception.AuthException;
import com.example.carrental.mapper.UserMapper;
import com.example.carrental.model.dto.client.LoginRequestDto;
import com.example.carrental.model.dto.client.RegisterRequestDto;
import com.example.carrental.model.dto.client.TokenResponseDto;
import com.example.carrental.repository.RoleRepository;
import com.example.carrental.repository.UserRepository;
import com.example.carrental.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public TokenResponseDto login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByUsername(loginRequestDto.getUsername()).orElseThrow(() -> new AuthException("Invalid credentials"));
        checkPasswordValidity(loginRequestDto, user);
        return getLoginResponse(user);
    }

    private void checkPasswordValidity(LoginRequestDto loginRequestDto, User user) {
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new AuthException("Invalid credentials");
        }
    }

    private TokenResponseDto getLoginResponse(User user) {
        return TokenResponseDto.builder()
                .accessToken(jwtService.generateToken(user.getId(), user.getUsername()))
                .build();
    }

    public TokenResponseDto register(RegisterRequestDto registerRequestDto) {
        checkUsernameIsUnique(registerRequestDto);
        User user = registerUser(registerRequestDto);
        return getRegisterResponse(user);
    }

    private void checkUsernameIsUnique(RegisterRequestDto registerData) {
        if (userRepository.findByUsername(registerData.getUsername()).isPresent()) {
            throw new AuthException("User with that username already exists");
        }
    }

    private User registerUser(RegisterRequestDto registerData) {
        User user = userMapper.toEntity(registerData);
        Role userRole = roleRepository.findByName(RoleEnum.USER);
        user.setRoles(Set.of(userRole));
        userRepository.save(user);
        return user;
    }

    private TokenResponseDto getRegisterResponse(User user) {
        return TokenResponseDto.builder()
                .accessToken(jwtService.generateToken(user.getId(), user.getUsername()))
                .build();
    }
}

