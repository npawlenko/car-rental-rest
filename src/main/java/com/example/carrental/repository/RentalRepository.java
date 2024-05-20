package com.example.carrental.repository;

import com.example.carrental.domain.Rental;
import com.example.carrental.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findAllByUser(User user);

    @Query("SELECT COUNT(r) > 0 FROM Rental r WHERE r.car.id=:carId AND r.dateFrom <= :dateTo AND r.dateTo >= :dateFrom")
    boolean existsByCarIdAndDateRange(Long carId, LocalDateTime dateFrom, LocalDateTime dateTo);

    @Query("SELECT COUNT(r) > 0 FROM Rental r WHERE r.id <> :rentalId AND  r.car.id=:carId AND r.dateFrom <= :dateTo AND r.dateTo >= :dateFrom")
    boolean existsByCarIdAndDateRangeExcludingRentalId(Long carId, LocalDateTime dateFrom, LocalDateTime dateTo, Long rentalId);
}
