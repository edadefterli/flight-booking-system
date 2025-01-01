package com.example.flightbooking.api.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
public class SeatRequest {
    @NotNull
    private String seatNumber;

    @NotNull
    private double price;

    @NotNull
    private Long flightId;
}
