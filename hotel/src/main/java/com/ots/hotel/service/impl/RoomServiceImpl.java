package com.ots.hotel.service.impl;

import com.ots.hotel.dto.RoomDTO;
import com.ots.hotel.entities.Room;
import com.ots.hotel.enums.RoomType;
import com.ots.hotel.repository.RoomRepository;
import com.ots.hotel.service.RoomService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public RoomDTO createRoom(RoomDTO roomDTO) {
        if (roomRepository.existsByRoomNumber(roomDTO.getRoomNumber())) {
            throw new IllegalArgumentException("Δωμάτιο με αριθμό " + roomDTO.getRoomNumber() + " υπάρχει ήδη.");
        }
        Room room = toEntity(roomDTO);
        room.setAvailable(true);
        return toDTO(roomRepository.save(room));
    }

    @Override
    public List<RoomDTO> getAvailableRooms() {
        return roomRepository.findByAvailableTrue()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomDTO> getAvailableRoomsByType(RoomType type) {
        return roomRepository.findByAvailableTrueAndType(type)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomDTO> getAllRooms() {
        return roomRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RoomDTO getRoomById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Δωμάτιο με id " + id + " δεν βρέθηκε."));
        return toDTO(room);
    }


    private RoomDTO toDTO(Room room) {
        RoomDTO dto = new RoomDTO();
        dto.setId(room.getId());
        dto.setRoomNumber(room.getRoomNumber());
        dto.setType(room.getType());
        dto.setPricePerNight(room.getPricePerNight());
        dto.setCapacity(room.getCapacity());
        dto.setDescription(room.getDescription());
        dto.setAvailable(room.getAvailable());
        return dto;
    }

    private Room toEntity(RoomDTO dto) {
        Room room = new Room();
        room.setRoomNumber(dto.getRoomNumber());
        room.setType(dto.getType());
        room.setPricePerNight(dto.getPricePerNight());
        room.setCapacity(dto.getCapacity());
        room.setDescription(dto.getDescription());
        room.setAvailable(dto.getAvailable() != null ? dto.getAvailable() : true);
        return room;
    }
}