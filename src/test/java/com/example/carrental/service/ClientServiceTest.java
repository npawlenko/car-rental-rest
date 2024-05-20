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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Test for class {@link ClientService}
 */
@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private ClientService clientService;

    @Test
    public void testLogin_Success() {
        // Arrange
        LoginRequestDto loginRequestDto = new LoginRequestDto("testuser", "password");
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("encodedPassword");
        when(passwordEncoder.matches("password", "encodedPassword")).thenReturn(true);
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(jwtService.generateToken(user.getId(), user.getUsername())).thenReturn("mockToken");

        // Act
        TokenResponseDto response = clientService.login(loginRequestDto);

        // Assert
        assertNotNull(response);
        assertEquals("mockToken", response.getAccessToken());
    }

    @Test
    public void testLogin_InvalidCredentials() {
        // Arrange
        LoginRequestDto loginRequestDto = new LoginRequestDto("testuser", "wrongpassword");
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(AuthException.class, () -> clientService.login(loginRequestDto));
    }

    @Test
    public void testRegister_Success() {
        // Arrange
        RegisterRequestDto registerRequestDto = new RegisterRequestDto("name", "name", "newuser", "password");
        Role userRole = new Role(1L, RoleEnum.USER);
        when(roleRepository.findByName(RoleEnum.USER)).thenReturn(userRole);
        when(userRepository.findByUsername("newuser")).thenReturn(Optional.empty());
        User user = new User();
        user.setUsername("newuser");
        user.setPassword(passwordEncoder.encode("password"));
        when(userMapper.toEntity(registerRequestDto)).thenReturn(user);
        when(jwtService.generateToken(user.getId(), user.getUsername())).thenReturn("mockToken");

        // Act
        TokenResponseDto response = clientService.register(registerRequestDto);

        // Assert
        assertNotNull(response);
        assertEquals("mockToken", response.getAccessToken());
    }

    @Test
    public void testRegister_UsernameAlreadyExists() {
        // Arrange
        RegisterRequestDto registerRequestDto = new RegisterRequestDto("name", "name","existinguser", "password");
        when(userRepository.findByUsername("existinguser")).thenReturn(Optional.of(new User()));

        // Act & Assert
        assertThrows(AuthException.class, () -> clientService.register(registerRequestDto));
    }
}