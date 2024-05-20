package com.example.carrental.web;

import com.example.carrental.domain.Car;
import com.example.carrental.model.dto.CarDto;
import com.example.carrental.security.annotation.RequireAdminRole;
import com.example.carrental.service.CarService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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
    public CarDto create(CarDto carDto) {
        return super.create(carDto);
    }

    @Override
    @RequireAdminRole
    public CarDto update(Long id, CarDto carDto) {
        return super.update(id, carDto);
    }

    @Override
    @RequireAdminRole
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
