package com.example.carrental.model.enums;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CarModel")
@XmlRootElement(name = "CarDto", namespace = "http://localhost:8080/services/car")
@Getter
public enum CarModel implements Serializable {
    A3(CarMake.AUDI);

    private final CarMake make;

    CarModel(CarMake make) {
        this.make = make;
    }

    private static Set<CarModel> ofMake(CarMake carMake) {
        return Arrays.stream(CarModel.values())
                .filter(carModel -> carModel.getMake().equals(carMake))
                .collect(Collectors.toSet());
    }
}
