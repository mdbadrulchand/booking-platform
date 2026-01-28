package com.booking.platform.controller;

import com.booking.platform.entity.Booking;
import com.booking.platform.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ticketBookings")
public class BookingController {
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/book")
    public ResponseEntity<Booking> bookTicket(@RequestParam Long userId, @RequestParam Long showId, @RequestParam Long seatId, @RequestParam Double amount) {
        Booking booking = bookingService.createBooking(userId, showId, seatId, amount);
        return ResponseEntity.ok(booking);
    }
}
