package com.ots.hotel.service;

import com.ots.hotel.dto.RoomDTO;
import com.ots.hotel.enums.RoomType;

import java.util.List;

public interface RoomService {

    RoomDTO createRoom(RoomDTO roomDTO);

    List<RoomDTO> getAvailableRooms();

    List<RoomDTO> getAvailableRoomsByType(RoomType type);

    List<RoomDTO> getAllRooms();

    RoomDTO getRoomById(Long id);
}