package com.ots.hotel.dto;

import com.ots.hotel.enums.BookingStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingDTO {

    private Long id;

    @NotNull
    private Long customerId;

    @NotNull
    private Long roomId;

    @NotNull
    private LocalDate checkIn;

    @NotNull
    private LocalDate checkOut;

    private BookingStatus status;

    private Double totalCost;
}