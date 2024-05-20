package com.example.carrental.mapper;

import com.example.carrental.domain.Role;
import com.example.carrental.model.dto.RoleDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public abstract class RoleMapper implements EntityMapper<RoleDto, Role> {
}