package com.example.carrental.mapper;

import com.example.carrental.domain.User;
import com.example.carrental.model.dto.DictionaryDto;
import com.example.carrental.model.dto.UserDto;
import com.example.carrental.model.dto.client.RegisterRequestDto;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public abstract class UserMapper implements EntityMapper<User, UserDto> {
    @Autowired
    private PasswordEncoder passwordEncoder;


    public abstract User toEntity(UserDto userDto);

    public abstract UserDto toDto(User entity);

    @Mapping(source = "username", target = "name")
    public abstract DictionaryDto toDictionary(User entity);

    public abstract void partialUpdate(UserDto userDto, @MappingTarget User entity);

    @Mapping(target = "password", qualifiedByName = "encryptPassword")
    public abstract User toEntity(RegisterRequestDto registerRequestDto);

    @Named("encryptPassword")
    protected String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
