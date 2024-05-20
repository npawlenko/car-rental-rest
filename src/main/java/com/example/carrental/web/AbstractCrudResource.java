package com.example.carrental.web;

import com.example.carrental.service.AbstractCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractCrudResource<T, DTO, ID> {
    protected abstract AbstractCrudService<T, DTO, ID> getService();

    @GetMapping("/{id}")
    public DTO findById(@PathVariable ID id) {
        return getService().findById(id);
    }

    @GetMapping
    public List<DTO> findAll() {
        return getService().findAll();
    }

    @PostMapping
    public DTO create(@RequestBody DTO dto) {
        return getService().create(dto);
    }

    @PutMapping
    public DTO update(@PathVariable ID id, @RequestBody DTO dto) {
        return getService().update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable ID id) {
        getService().deleteById(id);
    }
}
