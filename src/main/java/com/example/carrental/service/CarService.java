package com.example.carrental.service;

import com.example.carrental.domain.Car;
import com.example.carrental.mapper.CarMapper;
import com.example.carrental.model.dto.CarDto;
import com.example.carrental.repository.CarRepository;
import com.example.carrental.web.CarResource;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
public class CarService extends AbstractCrudService<Car, CarDto, Long> {
    private final CarRepository repository;
    private final CarMapper mapper;

    @Override
    protected void addLinks(EntityModel<CarDto> model, Long id) {
        model.add(
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CarResource.class).findById(id)).withSelfRel()
        );
    }
}
