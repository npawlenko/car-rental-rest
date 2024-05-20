package com.example.carrental.mapper;

import com.example.carrental.model.dto.DictionaryDto;
import org.mapstruct.MappingTarget;

public interface EntityMapper<T, DTO> {

    T toEntity(DTO dto);
    DTO toDto(T entity);
    DictionaryDto toDictionary(T entity);
    void partialUpdate(DTO dto, @MappingTarget T entity);
}
