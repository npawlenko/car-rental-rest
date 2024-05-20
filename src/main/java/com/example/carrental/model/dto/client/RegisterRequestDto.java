package com.example.carrental.model.dto.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RegisterRequestDto {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
}
