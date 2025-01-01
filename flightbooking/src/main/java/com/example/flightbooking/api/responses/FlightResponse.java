package com.example.flightbooking.api.responses;

import com.example.flightbooking.dto.SeatDto;
import com.example.flightbooking.entities.Flight;
import com.example.flightbooking.entities.Seat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class FlightResponse {
    private String name;
    private String description;
    private double price;
    private List<SeatDto> seats;

    public FlightResponse(Flight flight){
        this.name = flight.getName();
        this.description = flight.getDescription();
        this.price = flight.getPrice();
        this.seats = flight.getSeats().stream().map(seat -> {
            SeatDto seatDto = new SeatDto(seat.getId(),seat.getSeatNumber(),seat.isAvailable(),seat.getPrice(),seat.getFlight().getId());
            return seatDto;
        }).collect(Collectors.toList());
    }
}
