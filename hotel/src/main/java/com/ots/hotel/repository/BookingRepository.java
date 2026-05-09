package com.ots.hotel.repository;

import com.ots.hotel.entities.Booking;
import com.ots.hotel.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByCustomerId(Long customerId);

    List<Booking> findByStatus(BookingStatus status);

    @Query("SELECT b FROM Booking b WHERE b.room.id = :roomId " +
            "AND b.status != 'CANCELLED' " +
            "AND b.checkIn < :checkOut " +
            "AND b.checkOut > :checkIn")
    List<Booking> findOverlappingBookings(@Param("roomId") Long roomId,
                                          @Param("checkIn") LocalDate checkIn,
                                          @Param("checkOut") LocalDate checkOut);

    @Query("SELECT b FROM Booking b WHERE b.checkIn >= :from AND b.checkOut <= :to")
    List<Booking> findByDateRange(@Param("from") LocalDate from,
                                  @Param("to") LocalDate to);
}