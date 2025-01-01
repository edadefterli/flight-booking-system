package com.example.flightbooking.api.controllers;


import com.example.flightbooking.api.requests.FlightRequest;
import com.example.flightbooking.api.responses.FlightResponse;
import com.example.flightbooking.dto.FlightDto;
import com.example.flightbooking.services.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public ResponseEntity<List<FlightResponse>> getAllFlights(){
        return ResponseEntity.ok(flightService.getAllFlights());
    }

    @GetMapping("/{flightId}")
    public ResponseEntity<FlightResponse> getFlightById(@PathVariable Long flightId){
        return ResponseEntity.ok(flightService.getFlightById(flightId));
    }

    @PostMapping
    public ResponseEntity<FlightDto> createFlight(@RequestBody FlightRequest newFlight){
        return ResponseEntity.ok(flightService.createFlight(newFlight));
    }

    @PutMapping("/{flightId}")
    public ResponseEntity<FlightDto> updateFlight(@PathVariable Long flightId, @RequestBody FlightRequest newFlight){
        return ResponseEntity.ok(flightService.updateFlight(flightId,newFlight));
    }

    @DeleteMapping("/{flightId}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long flightId){
        flightService.deleteById(flightId);
        return ResponseEntity.noContent().build();
    }

}
