package com.example.carrental.web;

import com.example.carrental.domain.Rental;
import com.example.carrental.model.dto.CarDto;
import com.example.carrental.model.dto.RentalDto;
import com.example.carrental.security.annotation.RequireAdminRole;
import com.example.carrental.service.RentalService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
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
    public EntityModel<RentalDto> update(Long aLong, RentalDto rentalDto) {
        return super.update(aLong, rentalDto);
    }

    @Override
    @RequireAdminRole
    public ResponseEntity<Void> deleteById(Long aLong) {
        return super.deleteById(aLong);
    }
}