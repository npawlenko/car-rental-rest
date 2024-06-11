package com.example.carrental.service;

import com.example.carrental.domain.Car;
import com.example.carrental.domain.Rental;
import com.example.carrental.domain.RoleEnum;
import com.example.carrental.domain.User;
import com.example.carrental.exception.ClientRuntimeException;
import com.example.carrental.exception.NotFoundException;
import com.example.carrental.mapper.RentalMapper;
import com.example.carrental.model.dto.RentalDto;
import com.example.carrental.repository.CarRepository;
import com.example.carrental.repository.RentalRepository;
import com.example.carrental.security.SecurityUtils;
import com.example.carrental.web.CarResource;
import com.example.carrental.web.RentalResource;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Getter
public class RentalService extends AbstractCrudService<Rental, RentalDto, Long> {
    private final RentalRepository repository;
    private final RentalMapper mapper;

    private final CarRepository carRepository;

    @Override
    protected void addLinks(EntityModel<RentalDto> model, Long id) {
        model.add(
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RentalResource.class).findById(id)).withSelfRel()
        );
    }

    @Override
    public EntityModel<RentalDto> findById(Long id) {
        User user = SecurityUtils.getCurrentUser().orElseThrow(() -> new ClientRuntimeException("You should be logged in to access this endpoint"));
        Rental rental = getRepository().findById(id).orElseThrow(NotFoundException::new);
        if (!SecurityUtils.hasRole(user, RoleEnum.ADMIN) && !rental.getUser().equals(user)) {
            throw new NotFoundException();
        }
        return EntityModel.of(getMapper().toDto(rental));
    }

    @Override
    public List<EntityModel<RentalDto>> findAll() {
        User user = SecurityUtils.getCurrentUser().orElseThrow(() -> new ClientRuntimeException("You should be logged in to access this endpoint"));
        List<Rental> rentalList;
        if (SecurityUtils.hasRole(user, RoleEnum.ADMIN)) {
            rentalList = getRepository().findAll();
        } else {
            rentalList = getRepository().findAllByUser(user);
        }
        return rentalList
                .stream()
                .map(getMapper()::toDto)
                .map(EntityModel::of)
                .collect(Collectors.toList());
    }

    @Override
    public EntityModel<RentalDto> create(RentalDto rentalDto) {
        User user = SecurityUtils.getCurrentUser().orElseThrow(() -> new ClientRuntimeException("You should be logged in to access this endpoint"));
        if (SecurityUtils.hasRole(user, RoleEnum.ADMIN)) {
            throw new ClientRuntimeException("Can not rent as admin");
        }
        Car car = carRepository.findById(rentalDto.getCar().getId()).orElseThrow(() -> new NotFoundException("Car was not found"));
        if (getRepository().existsByCarIdAndDateRange(car.getId(), rentalDto.getDateFrom(), rentalDto.getDateTo())) {
            throw new ClientRuntimeException("Rental for the specified car conflicts with another rental");
        }

        Rental rental = getMapper().toEntity(rentalDto);
        rental.setUser(user);
        rental.setCar(car);

        Rental savedRental = getRepository().save(rental);
        return EntityModel.of(getMapper().toDto(savedRental));
    }

    @Override
    public EntityModel<RentalDto> update(Long id, RentalDto rentalDto) {
        Rental rental = getRepository().findById(id).orElseThrow(NotFoundException::new);
        Car car = carRepository.findById(rentalDto.getCar().getId()).orElseThrow(() -> new NotFoundException("Car was not found"));
        if (getRepository().existsByCarIdAndDateRangeExcludingRentalId(car.getId(), rentalDto.getDateFrom(), rentalDto.getDateTo(), id)) {
            throw new ClientRuntimeException("Rental for the specified car conflicts with another rental");
        }

        getMapper().partialUpdate(rentalDto, rental);
        Rental savedRental = getRepository().save(rental);
        return EntityModel.of(getMapper().toDto(savedRental));
    }

    @Override
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }
}