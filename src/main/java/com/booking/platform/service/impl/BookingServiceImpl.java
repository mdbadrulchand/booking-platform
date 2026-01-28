package com.booking.platform.service.impl;

import com.booking.platform.entity.Booking;
import com.booking.platform.exception.ResourceNotFoundException;
import com.booking.platform.repository.BookingRepository;
import com.booking.platform.service.BookingService;
import com.booking.platform.service.client.InventoryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final InventoryClient inventoryClient;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, InventoryClient inventoryClient) {
        this.bookingRepository = bookingRepository;
        this.inventoryClient = inventoryClient;
    }

    @Override
    public Booking createBooking(Long userId, Long showId, Long seatId, Double amount) {
        // First, check inventory in another service via RestTemplate
        if (!inventoryClient.checkSeatAvailability(showId, seatId)) {
            throw new ResourceNotFoundException("Seat is not available");
        }
        // Proceed with bookingDto logic (e.g., call Payment service)
        Booking newBooking = new Booking();
        newBooking.setUserId(userId);
        newBooking.setShowId(showId);
        newBooking.setTotalAmount(amount);
        newBooking.setStatus("PENDING");
        newBooking.setBookingTime(LocalDateTime.now());
        return this.bookingRepository.save(newBooking);

    }
}
