package com.example.flightbooking.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SeatDto {
    private Long id;
    private String seatNumber;
    private boolean isAvailable;
    private double price;
    private Long flightId;

    public SeatDto(Long id, String seatNumber, boolean isAvailable, double price, Long flightId) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.isAvailable = isAvailable;
        this.price = price;
        this.flightId = flightId;
    }


}
