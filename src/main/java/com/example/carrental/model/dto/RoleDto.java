package com.example.carrental.model.dto;

import com.example.carrental.domain.Role;
import com.example.carrental.domain.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link Role}
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class RoleDto implements Serializable {
    private Long id;
    private RoleEnum name;
}