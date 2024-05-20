package com.example.carrental.service;

import com.example.carrental.domain.Car;
import com.example.carrental.mapper.CarMapper;
import com.example.carrental.model.dto.CarDto;
import com.example.carrental.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarMapper carMapper;

    @InjectMocks
    private CarService carService;

    @Test
    void testFindById() {
        // Arrange
        Long carId = 1L;
        Car car = new Car();
        car.setId(carId);
        CarDto carDto = new CarDto();
        carDto.setId(carId);

        when(carRepository.findById(carId)).thenReturn(Optional.of(car));
        when(carMapper.toDto(car)).thenReturn(carDto);

        // Act
        CarDto result = carService.findById(carId);

        // Assert
        assertEquals(carDto.getId(), result.getId());
    }

    @Test
    void testFindAll() {
        // Arrange
        Car car1 = new Car();
        car1.setId(1L);
        Car car2 = new Car();
        car2.setId(2L);
        List<Car> cars = Arrays.asList(car1, car2);

        CarDto carDto1 = new CarDto();
        carDto1.setId(1L);
        CarDto carDto2 = new CarDto();
        carDto2.setId(2L);
        List<CarDto> carDtos = Arrays.asList(carDto1, carDto2);

        when(carRepository.findAll()).thenReturn(cars);
        when(carMapper.toDto(car1)).thenReturn(carDto1);
        when(carMapper.toDto(car2)).thenReturn(carDto2);

        // Act
        List<CarDto> result = carService.findAll();

        // Assert
        assertEquals(carDtos.size(), result.size());
        assertEquals(carDtos.get(0).getId(), result.get(0).getId());
        assertEquals(carDtos.get(1).getId(), result.get(1).getId());
    }

    @Test
    void testCreate() {
        // Arrange
        CarDto carDto = new CarDto();
        Car car = new Car();

        when(carMapper.toEntity(carDto)).thenReturn(car);
        when(carMapper.toDto(car)).thenReturn(carDto);
        when(carRepository.save(car)).thenReturn(car);

        // Act
        CarDto result = carService.create(carDto);

        // Assert
        assertEquals(carDto, result);
    }

    @Test
    void testUpdate() {
        // Arrange
        Long carId = 1L;
        CarDto carDto = new CarDto();
        carDto.setId(carId);
        Car car = new Car();
        car.setId(carId);

        when(carRepository.findById(carId)).thenReturn(Optional.of(car));
        when(carRepository.save(car)).thenReturn(car);
        when(carMapper.toDto(car)).thenReturn(carDto);

        // Act
        CarDto result = carService.update(carId, carDto);

        // Assert
        assertEquals(carDto.getId(), result.getId());
    }

    @Test
    void testDeleteById() {
        // Arrange
        Long carId = 1L;

        // Act
        carService.deleteById(carId);

        // Assert
        verify(carRepository, times(1)).deleteById(carId);
    }
}
