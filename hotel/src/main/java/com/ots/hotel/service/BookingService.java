package com.ots.hotel.service;

import com.ots.hotel.dto.BookingDTO;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    BookingDTO createBooking(BookingDTO bookingDTO);

    boolean isRoomAvailable(Long roomId, LocalDate checkIn, LocalDate checkOut);

    BookingDTO cancelBooking(Long bookingId);

    List<BookingDTO> getBookingsByCustomer(Long customerId);

    List<BookingDTO> getBookingsByDateRange(LocalDate from, LocalDate to);

    List<BookingDTO> getAllBookings();

    BookingDTO getBookingById(Long id);
}