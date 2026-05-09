package com.ots.hotel.repository;

import com.ots.hotel.entities.Room;
import com.ots.hotel.enums.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByAvailableTrue();

    List<Room> findByType(RoomType type);

    List<Room> findByAvailableTrueAndType(RoomType type);

    boolean existsByRoomNumber(Integer roomNumber);
}