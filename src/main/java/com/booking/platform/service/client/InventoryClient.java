package com.booking.platform.service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class InventoryClient {

    private final RestTemplate restTemplate;

    @Autowired
    public InventoryClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean checkSeatAvailability(Long showId, Long seatId) {
        // Assume the Theatre service runs on a different port/hostname
        String url = "http://localhost:8081/api/inventory/check?showId=" + showId + "&seatId=" + seatId;
        // This is a basic call; in production you would handle potential exceptions
        // and network issues more robustly.
        Boolean available = restTemplate.getForObject(url, Boolean.class);
        return Boolean.TRUE.equals(available);
    }
}
