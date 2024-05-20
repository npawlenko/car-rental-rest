package com.example.carrental.mapper;

import com.example.carrental.domain.Rental;
import com.example.carrental.model.dto.RentalDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class RentalMapper implements EntityMapper<Rental, RentalDto> {
    @Mapping(source = "user.id", target = "user.id")
    @Mapping(source = "car.id", target = "car.id")
    public abstract RentalDto toDto(Rental rental);

    @InheritInverseConfiguration(name = "toDto")
    @Mapping(target = "id", ignore = true)
    public abstract Rental toEntity(RentalDto rentalDto);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void partialUpdate(RentalDto rentalDto, @MappingTarget Rental rental);
}