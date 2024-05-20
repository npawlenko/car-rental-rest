package com.example.carrental.model.dto;

import com.example.carrental.domain.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link User}
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class UserDto implements Serializable {
    private Long id;
    private String firstname;
    private String lastname;
    private @NotNull String username;
    private Set<RoleDto> roles;
}