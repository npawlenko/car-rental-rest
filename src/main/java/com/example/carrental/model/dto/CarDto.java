package com.example.carrental.model.dto;

import com.example.carrental.domain.Car;
import com.example.carrental.model.enums.CarModel;
import com.example.carrental.model.enums.Drive;
import com.example.carrental.model.enums.Fuel;
import com.example.carrental.model.enums.Transmission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link Car}
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public final class CarDto implements Serializable {
    private Long id;
    private CarModel model;
    private Transmission transmission;
    private String color;
    private Integer mileage;
    private Integer yearOfProduction;
    private Fuel fuel;
    private Fuel secondaryFuel;
    private Drive drive;
    private Boolean airConditioning;
    private Float pricePerHour;
    private byte[] picture;
}