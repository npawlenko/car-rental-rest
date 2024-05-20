package com.example.carrental.domain;

import com.example.carrental.model.enums.CarModel;
import com.example.carrental.model.enums.Drive;
import com.example.carrental.model.enums.Fuel;
import com.example.carrental.model.enums.Transmission;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = Car.TABLE_NAME)
@SequenceGenerator(name = Car.SEQ_GEN, sequenceName = Car.SEQ_NAME, allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {
    public static final String TABLE_NAME = "CARS";
    public static final String SEQ_GEN = "CARS_SEQ_GEN";
    public static final String SEQ_NAME = "CARS_SEQ";

    public static final String COL_ID = "CAR_ID";
    public static final String COL_MODEL = "MODEL";
    public static final String COL_TRANSMISSION = "TRANSMISSION";
    public static final String COL_COLOR = "COLOR";
    public static final String COL_MILEAGE = "MILEAGE";
    public static final String COL_YEAR_OF_PRODUCTION = "YEAR_OF_PRODUCTION";
    public static final String COL_FUEL = "FUEL";
    public static final String COL_SECONDARY_FUEL = "SECONDARY_FUEL";
    public static final String COL_DRIVE = "DRIVE";
    public static final String COL_AIR_CONDITIONING = "AIR_CONDITIONING";
    public static final String COL_PRICE_PER_HOUR = "PRICE_PER_HOUR";
    public static final String COL_PICTURE = "PICTURE";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_GEN)
    @Column(name = COL_ID)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = COL_MODEL)
    private CarModel model;

    @Enumerated(EnumType.STRING)
    @Column(name = COL_TRANSMISSION)
    private Transmission transmission;

    @Column(name = COL_COLOR)
    private String color;

    @Column(name = COL_MILEAGE)
    /**
     * Mileage in km
     */
    private Integer mileage;

    @Column(name = COL_YEAR_OF_PRODUCTION)
    private Integer yearOfProduction;

    @Enumerated(EnumType.STRING)
    @Column(name = COL_FUEL)
    private Fuel fuel;

    @Enumerated(EnumType.STRING)
    @Column(name = COL_SECONDARY_FUEL)
    private Fuel secondaryFuel;

    @Enumerated(EnumType.STRING)
    @Column(name = COL_DRIVE)
    private Drive drive;

    @Column(name = COL_AIR_CONDITIONING)
    private Boolean airConditioning;

    @Column(name = COL_PRICE_PER_HOUR)
    private Float pricePerHour;

    @Column(name = COL_PICTURE, columnDefinition = "BYTEA")
    private byte[] picture;
}
