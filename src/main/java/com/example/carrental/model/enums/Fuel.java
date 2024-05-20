package com.example.carrental.model.enums;

import com.example.carrental.model.Translatable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Fuel")
@XmlRootElement(name = "CarDto", namespace = "http://localhost:8080/services/car")
public enum Fuel implements Translatable {
    GAS,
    LPG,
    DIESEL,
    ELECTRIC,
}
