package com.ots.hotel.entities;

import com.ots.hotel.enums.RoomType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "rooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_number", unique = true, nullable = false)
    @NotNull
    private Integer roomNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    private RoomType type;

    @Column(name = "price_per_night", nullable = false)
    @Positive
    private Double pricePerNight;

    @Column(nullable = false)
    @Positive
    private Integer capacity;

    @Column
    private String description;

    @Column(nullable = false)
    private Boolean available = true;
}