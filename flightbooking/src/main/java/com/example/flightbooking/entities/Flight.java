package com.example.flightbooking.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
public class Flight{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String departureAirport;
    private String arrivalAirport;
    private String airline;
    private double price;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    @OneToMany(mappedBy = "flight",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats;

}
