package com.example.carrental.security.filter;

import com.example.carrental.security.jwt.JwtAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationFilter extends AbstractAuthenticationFilter {
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);

        setRequestMatcher(
                request -> !request.getRequestURI().startsWith("/services/client")
        );
    }

    @Override
    public AuthenticationConverter getAuthenticationConverter() {
        return request -> {
            String authorizationHeader = request.getHeader("Authorization");
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                return new JwtAuthenticationToken(authorizationHeader.substring(7));
            }
            return null;
        };
    }
}
