package com.ots.hotel.service.impl;

import com.ots.hotel.dto.BookingDTO;
import com.ots.hotel.enums.BookingStatus;
import com.ots.hotel.entities.Booking;
import com.ots.hotel.entities.Customer;
import com.ots.hotel.entities.Room;
import com.ots.hotel.repository.BookingRepository;
import com.ots.hotel.repository.CustomerRepository;
import com.ots.hotel.repository.RoomRepository;
import com.ots.hotel.service.BookingService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        if (bookingDTO.getCheckIn().isAfter(bookingDTO.getCheckOut()) ||
                bookingDTO.getCheckIn().isEqual(bookingDTO.getCheckOut())) {
            throw new IllegalArgumentException("Οι ημερομηνίες check-in/check-out δεν είναι έγκυρες.");
        }

        Customer customer = customerRepository.findById(bookingDTO.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("Πελάτης δεν βρέθηκε."));

        Room room = roomRepository.findById(bookingDTO.getRoomId())
                .orElseThrow(() -> new EntityNotFoundException("Δωμάτιο δεν βρέθηκε."));

        if (!isRoomAvailable(room.getId(), bookingDTO.getCheckIn(), bookingDTO.getCheckOut())) {
            throw new IllegalStateException("Το δωμάτιο δεν είναι διαθέσιμο για το συγκεκριμένο διάστημα.");
        }

        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setRoom(room);
        booking.setCheckIn(bookingDTO.getCheckIn());
        booking.setCheckOut(bookingDTO.getCheckOut());
        booking.setStatus(BookingStatus.CONFIRMED);
        booking.setTotalCost(calculateTotalCost(room.getPricePerNight(),
                bookingDTO.getCheckIn(), bookingDTO.getCheckOut()));

        return toDTO(bookingRepository.save(booking));
    }

    @Override
    public boolean isRoomAvailable(Long roomId, LocalDate checkIn, LocalDate checkOut) {
        return bookingRepository.findOverlappingBookings(roomId, checkIn, checkOut).isEmpty();
    }

    @Override
    @Transactional
    public BookingDTO cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Κράτηση με id " + bookingId + " δεν βρέθηκε."));

        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new IllegalStateException("Η κράτηση είναι ήδη ακυρωμένη.");
        }

        booking.setStatus(BookingStatus.CANCELLED);
        return toDTO(bookingRepository.save(booking));
    }

    @Override
    public List<BookingDTO> getBookingsByCustomer(Long customerId) {
        return bookingRepository.findByCustomerId(customerId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingDTO> getBookingsByDateRange(LocalDate from, LocalDate to) {
        return bookingRepository.findByDateRange(from, to)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingDTO> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookingDTO getBookingById(Long id) {
        return toDTO(bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Κράτηση με id " + id + " δεν βρέθηκε.")));
    }

    private double calculateTotalCost(Double pricePerNight, LocalDate checkIn, LocalDate checkOut) {
        long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
        return nights * pricePerNight;
    }

    private BookingDTO toDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setId(booking.getId());
        dto.setCustomerId(booking.getCustomer().getId());
        dto.setRoomId(booking.getRoom().getId());
        dto.setCheckIn(booking.getCheckIn());
        dto.setCheckOut(booking.getCheckOut());
        dto.setStatus(booking.getStatus());
        dto.setTotalCost(booking.getTotalCost());
        return dto;
    }
}