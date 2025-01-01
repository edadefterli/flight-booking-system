package com.example.flightbooking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private int version;
    private String seatNumber;
    private boolean isAvailable = true;
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id",referencedColumnName = "id")
    @ToString.Exclude
    private Flight flight;

}
