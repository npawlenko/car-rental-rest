package com.example.carrental.model.dto;

import com.example.carrental.domain.Rental;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Rental}
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public final class RentalDto implements Serializable {
    private Long id;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private DictionaryDto car;
    private DictionaryDto user;
}