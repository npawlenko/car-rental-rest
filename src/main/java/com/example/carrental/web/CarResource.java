package com.example.carrental.web;

import com.example.carrental.domain.Car;
import com.example.carrental.model.dto.CarDto;
import com.example.carrental.security.annotation.RequireAdminRole;
import com.example.carrental.service.CarService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/car")
@RequiredArgsConstructor
@Getter
public class CarResource extends AbstractCrudResource<Car, CarDto, Long> {
    private final CarService service;

    @Override
    @RequireAdminRole
    public EntityModel<CarDto> create(CarDto carDto) {
        return super.create(carDto);
    }

    @Override
    @RequireAdminRole
    public EntityModel<CarDto> update(Long id, CarDto carDto) {
        return super.update(id, carDto);
    }

    @Override
    @RequireAdminRole
    public ResponseEntity<Void> deleteById(Long id) {
        return super.deleteById(id);
    }
}
