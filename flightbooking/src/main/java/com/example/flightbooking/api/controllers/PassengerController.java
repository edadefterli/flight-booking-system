package com.example.flightbooking.api.controllers;

import com.example.flightbooking.api.requests.PassengerRequest;
import com.example.flightbooking.dto.PassengerDto;
import com.example.flightbooking.entities.Passenger;
import com.example.flightbooking.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passengers")
public class PassengerController {
    private PassengerService passengerService;

    @Autowired
    public PassengerController(PassengerService passengerService){
        this.passengerService = passengerService;
    }

    @GetMapping
    public List<Passenger> getAllPassengers(){
        return passengerService.getAllPassengers();
    }

    @GetMapping("/{passengerId}")
    public ResponseEntity<Passenger> getPassengerById(@PathVariable Long passengerId){
        return ResponseEntity.ok(passengerService.getById(passengerId));
    }

    @PostMapping
    public ResponseEntity<PassengerDto> createUser(@RequestBody PassengerRequest newPassenger){
        return ResponseEntity.ok(passengerService.createPassenger(newPassenger));
    }

    @DeleteMapping("/{passengerId}")
    public void deletePassenger(@PathVariable Long passengerId){
        passengerService.deleteById(passengerId);
    }
}
