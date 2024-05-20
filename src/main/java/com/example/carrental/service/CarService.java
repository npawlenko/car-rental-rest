package com.example.carrental.service;

import com.example.carrental.domain.Car;
import com.example.carrental.mapper.CarMapper;
import com.example.carrental.model.dto.CarDto;
import com.example.carrental.repository.CarRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
public class CarService extends AbstractCrudService<Car, CarDto, Long> {
    private final CarRepository repository;
    private final CarMapper mapper;
}
