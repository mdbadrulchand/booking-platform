package com.booking.platform.service;

import com.booking.platform.entity.Booking;

public interface BookingService {

    Booking createBooking( Long userId, Long showId, Long seatId, Double amount);
}
