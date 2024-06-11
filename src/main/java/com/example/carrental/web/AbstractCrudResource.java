package com.example.carrental.web;

import com.example.carrental.domain.DomainEntity;
import com.example.carrental.service.AbstractCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractCrudResource<T extends DomainEntity<ID>, DTO, ID> {
    protected abstract AbstractCrudService<T, DTO, ID> getService();

    @GetMapping("/{id}")
    public EntityModel<DTO> findById(@PathVariable ID id) {
        return getService().findById(id);
    }

    @GetMapping
    public List<EntityModel<DTO>> findAll() {
        return getService().findAll();
    }

    @PostMapping
    public EntityModel<DTO> create(@RequestBody DTO dto) {
        return getService().create(dto);
    }

    @PutMapping("/{id}")
    public EntityModel<DTO> update(@PathVariable ID id, @RequestBody DTO dto) {
        return getService().update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable ID id) {
        getService().deleteById(id);
        return ResponseEntity.ok().build();
    }
}
