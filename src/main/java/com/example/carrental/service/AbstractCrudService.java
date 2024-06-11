package com.example.carrental.service;

import com.example.carrental.domain.DomainEntity;
import com.example.carrental.exception.NotFoundException;
import com.example.carrental.mapper.EntityMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.hateoas.EntityModel;

import java.util.AbstractMap;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractCrudService<T extends DomainEntity<ID>, DTO, ID> {
    protected abstract EntityMapper<T, DTO> getMapper();
    protected abstract JpaRepository<T, ID> getRepository();

    protected abstract void addLinks(EntityModel<DTO> model, ID id);

    public EntityModel<DTO> findById(ID id) {
        T foundEntity = getRepository().findById(id).orElseThrow(NotFoundException::new);
        EntityModel<DTO> entityModel = EntityModel.of(getMapper().toDto(foundEntity));
        addLinks(entityModel, id);
        return entityModel;
    }

    public List<EntityModel<DTO>> findAll() {
        return getRepository().findAll()
                .stream()
                .map(entity -> new AbstractMap.SimpleEntry<>(entity, getMapper().toDto(entity)))
                .map(pair -> {
                    T entity = pair.getKey();
                    DTO dto = pair.getValue();
                    ID id = entity.getId();
                    EntityModel<DTO> entityModel = EntityModel.of(dto);
                    addLinks(entityModel, id);
                    return entityModel;
                })
                .collect(Collectors.toList());
    }

    public EntityModel<DTO> create(DTO dto) {
        T entity = getMapper().toEntity(dto);
        T savedEntity = getRepository().save(entity);
        EntityModel<DTO> entityModel = EntityModel.of(getMapper().toDto(savedEntity));
        addLinks(entityModel, savedEntity.getId());
        return entityModel;
    }

    public EntityModel<DTO> update(ID id, DTO dto) {
        T entity = getRepository().findById(id).orElseThrow(NotFoundException::new);
        getMapper().partialUpdate(dto, entity);
        T updatedEntity = getRepository().save(entity);
        EntityModel<DTO> entityModel = EntityModel.of(getMapper().toDto(updatedEntity));
        addLinks(entityModel, id);
        return entityModel;
    }

    public void deleteById(ID id) {
        getRepository().deleteById(id);
    }
}
