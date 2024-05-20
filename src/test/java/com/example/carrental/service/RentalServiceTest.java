package com.example.carrental.service;

import com.example.carrental.domain.Car;
import com.example.carrental.domain.Rental;
import com.example.carrental.domain.RoleEnum;
import com.example.carrental.domain.User;
import com.example.carrental.exception.ClientRuntimeException;
import com.example.carrental.mapper.RentalMapper;
import com.example.carrental.model.dto.DictionaryDto;
import com.example.carrental.model.dto.RentalDto;
import com.example.carrental.repository.CarRepository;
import com.example.carrental.repository.RentalRepository;
import com.example.carrental.security.SecurityUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RentalServiceTest {

    @Mock
    private RentalRepository rentalRepository;

    @Mock
    private CarRepository carRepository;

    @Mock
    private RentalMapper rentalMapper;

    @Mock
    private SecurityUtils securityUtils;

    @InjectMocks
    private RentalService rentalService;

    private RentalDto rentalDto;
    private Car car;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("testuser");

        car = new Car();
        car.setId(1L);

        rentalDto = new RentalDto();
        rentalDto.setId(1L);
        rentalDto.setUser(DictionaryDto.builder().id(1L).name("testuser").build());
        rentalDto.setCar(DictionaryDto.builder().id(1L).name("AUDI A3").build());
        rentalDto.setDateFrom(LocalDateTime.now());
        rentalDto.setDateTo(LocalDateTime.now().plusMinutes(5));
    }

    @Test
    void testFindAll_AdminRole_Success() {
        // Arrange
        try (MockedStatic<SecurityUtils> utilities = Mockito.mockStatic(SecurityUtils.class)) {
            utilities.when(SecurityUtils::getCurrentUser).thenReturn(Optional.of(user));
            utilities.when(() -> SecurityUtils.hasRole(user, RoleEnum.ADMIN)).thenReturn(true);
            Rental rental1 = new Rental();
            Rental rental2 = new Rental();
            when(rentalRepository.findAll()).thenReturn(Arrays.asList(rental1, rental2));
            when(rentalMapper.toDto(rental1)).thenReturn(rentalDto);
            when(rentalMapper.toDto(rental2)).thenReturn(rentalDto);

            // Act
            List<RentalDto> result = rentalService.findAll();

            // Assert
            assertEquals(2, result.size());
        }
    }

    @Test
    void testFindAll_UserRole_Success() {
        try (MockedStatic<SecurityUtils> utilities = Mockito.mockStatic(SecurityUtils.class)) {
            // Arrange
            utilities.when(SecurityUtils::getCurrentUser).thenReturn(Optional.of(user));
            utilities.when(() -> SecurityUtils.hasRole(user, RoleEnum.ADMIN)).thenReturn(false);
            Rental rental1 = new Rental();
            Rental rental2 = new Rental();
            rental1.setUser(user);
            rental2.setUser(user);
            when(rentalRepository.findAllByUser(user)).thenReturn(Arrays.asList(rental1, rental2));
            when(rentalMapper.toDto(rental1)).thenReturn(rentalDto);
            when(rentalMapper.toDto(rental2)).thenReturn(rentalDto);

            // Act
            List<RentalDto> result = rentalService.findAll();

            // Assert
            assertEquals(2, result.size());
        }
    }

    @Test
    void testCreate_Success() {
        try (MockedStatic<SecurityUtils> utilities = Mockito.mockStatic(SecurityUtils.class)) {
            // Arrange
            utilities.when(SecurityUtils::getCurrentUser).thenReturn(Optional.of(user));
            Rental rental = new Rental();
            when(rentalMapper.toEntity(rentalDto)).thenReturn(rental);
            when(rentalRepository.existsByCarIdAndDateRange(car.getId(), rentalDto.getDateFrom(), rentalDto.getDateTo())).thenReturn(false);
            when(carRepository.findById(car.getId())).thenReturn(Optional.of(car));
            when(rentalRepository.save(rental)).thenReturn(rental);
            when(rentalMapper.toDto(rental)).thenReturn(rentalDto);

            // Act
            RentalDto result = rentalService.create(rentalDto);

            // Assert
            assertNotNull(result);
            assertEquals(rentalDto, result);
        }
    }

    @Test
    void testUpdate_Success() {
        // Arrange
        Long id = 1L;
        Rental rental = new Rental();
        RentalDto updatedRentalDto = rentalDto;
        updatedRentalDto.setDateTo(LocalDateTime.now().plusMinutes(10));
        when(rentalRepository.findById(id)).thenReturn(Optional.of(rental));
        when(rentalMapper.toDto(rental)).thenReturn(rentalDto);
        when(carRepository.findById(rentalDto.getCar().getId())).thenReturn(Optional.of(car));
        when(rentalRepository.existsByCarIdAndDateRangeExcludingRentalId(car.getId(), updatedRentalDto.getDateFrom(), updatedRentalDto.getDateTo(), id)).thenReturn(false);
        when(rentalRepository.save(rental)).thenReturn(rental);
        when(rentalMapper.toDto(rental)).thenReturn(updatedRentalDto);

        // Act
        RentalDto result = rentalService.update(id, updatedRentalDto);

        // Assert
        assertNotNull(result);
        assertEquals(updatedRentalDto, result);
    }

    @Test
    void testUpdate_Conflict() {
        // Arrange
        Long id = 1L;
        Rental rental = new Rental();
        RentalDto updatedRentalDto = rentalDto;
        updatedRentalDto.setDateTo(LocalDateTime.now().plusMinutes(10));
        when(rentalRepository.findById(id)).thenReturn(Optional.of(rental));
        when(carRepository.findById(rentalDto.getCar().getId())).thenReturn(Optional.of(car));
        when(rentalRepository.existsByCarIdAndDateRangeExcludingRentalId(car.getId(), updatedRentalDto.getDateFrom(), updatedRentalDto.getDateTo(), id)).thenReturn(true);

        // Act & Assert
        assertThrows(ClientRuntimeException.class, () -> rentalService.update(id, updatedRentalDto));
    }

    @Test
    void testDeleteById_Success() {
        // Arrange
        Long id = 1L;

        // Act
        assertDoesNotThrow(() -> rentalService.deleteById(id));
    }
}
