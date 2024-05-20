package com.example.carrental.service;

import com.example.carrental.exception.NotFoundException;
import com.example.carrental.mapper.EntityMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractCrudService<T, DTO, ID> {
    protected abstract EntityMapper<T, DTO> getMapper();
    protected abstract JpaRepository<T, ID> getRepository();

    public DTO findById(ID id) {
        T foundEntity = getRepository().findById(id).orElseThrow(NotFoundException::new);
        return getMapper().toDto(foundEntity);
    }

    public List<DTO> findAll() {
        return getRepository().findAll()
                .stream()
                .map(getMapper()::toDto)
                .collect(Collectors.toList());
    }

    public DTO create(DTO dto) {
        T entity = getMapper().toEntity(dto);
        T savedEntity = getRepository().save(entity);
        return getMapper().toDto(savedEntity);
    }

    public DTO update(ID id, DTO dto) {
        T entity = getRepository().findById(id).orElseThrow(NotFoundException::new);
        getMapper().partialUpdate(dto, entity);
        T updatedEntity = getRepository().save(entity);
        return getMapper().toDto(updatedEntity);
    }

    public void deleteById(ID id) {
        getRepository().deleteById(id);
    }
}
