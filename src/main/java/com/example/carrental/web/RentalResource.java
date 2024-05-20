package com.example.carrental.web;

import com.example.carrental.domain.Rental;
import com.example.carrental.model.dto.RentalDto;
import com.example.carrental.security.annotation.RequireAdminRole;
import com.example.carrental.service.RentalService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rental")
@RequiredArgsConstructor
@Getter
public class RentalResource extends AbstractCrudResource<Rental, RentalDto, Long> {
    private final RentalService service;

    @Override
    @RequireAdminRole
    public RentalDto update(Long aLong, RentalDto rentalDto) {
        return super.update(aLong, rentalDto);
    }

    @Override
    @RequireAdminRole
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }
}