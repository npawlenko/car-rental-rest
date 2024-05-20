package com.example.carrental.security;

import com.example.carrental.domain.Role;
import com.example.carrental.domain.RoleEnum;
import com.example.carrental.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SecurityUtils {

    public static Optional<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return Optional.empty();
        }

        if (authentication.getPrincipal() instanceof User user) {
            return Optional.ofNullable(user);
        }
        return Optional.empty();
    }

    public static boolean hasRole(User user, RoleEnum roleEnum) {
        return user.getRoles().stream().map(Role::getName).filter(r -> r.equals(roleEnum)).findFirst().isPresent();
    }
}
