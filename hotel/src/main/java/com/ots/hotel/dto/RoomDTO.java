package com.ots.hotel.dto;

import com.ots.hotel.enums.RoomType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class RoomDTO {

    private Long id;

    @NotNull
    private Integer roomNumber;

    @NotNull
    private RoomType type;

    @Positive
    private Double pricePerNight;

    @Positive
    private Integer capacity;

    private String description;

    private Boolean available;
}