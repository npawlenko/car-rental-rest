package com.example.carrental.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = Rental.TABLE_NAME)
@SequenceGenerator(name = Rental.SEQ_GEN, sequenceName = Rental.SEQ_NAME, allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rental {
    public static final String TABLE_NAME = "RENTALS";
    public static final String SEQ_GEN = "RENTALS_SEQ_GEN";
    public static final String SEQ_NAME = "RENTALS_SEQ";

    public static final String COL_ID = "RENTAL_ID";
    public static final String COL_DATE_FROM = "DATE_FROM";
    public static final String COL_DATE_TO = "DATE_TO";
    public static final String COL_CAR_ID = "CAR_ID";
    public static final String COL_USER_ID = "USER_ID";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_GEN)
    @Column(name = COL_ID)
    private Long id;

    @Column(name = COL_DATE_FROM)
    private LocalDateTime dateFrom;

    @Column(name = COL_DATE_TO)
    private LocalDateTime dateTo;

    @ManyToOne
    @JoinColumn(name = COL_CAR_ID, referencedColumnName = Car.COL_ID)
    private Car car;

    @ManyToOne
    @JoinColumn(name = COL_USER_ID, referencedColumnName = User.COL_ID)
    private User user;
}
