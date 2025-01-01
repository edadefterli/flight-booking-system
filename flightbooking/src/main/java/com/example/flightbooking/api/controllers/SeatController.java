package com.example.flightbooking.api.controllers;

import com.example.flightbooking.api.requests.SeatRequest;
import com.example.flightbooking.dto.SeatDto;
import com.example.flightbooking.entities.Seat;
import com.example.flightbooking.services.SeatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
public class SeatController {
    private SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping
    public ResponseEntity<List<SeatDto>> getAllSeats(){
        return ResponseEntity.ok(seatService.getAllSeats());
    }

    @PostMapping
    public ResponseEntity<SeatDto> createSeat(@RequestBody SeatRequest newSeat){
        return ResponseEntity.ok(seatService.createSeat(newSeat));
    }

    @PutMapping("/{seatId}")
    public ResponseEntity<SeatDto> updateSeat(@PathVariable Long seatId, @RequestBody SeatRequest newSeat){
        return ResponseEntity.ok(seatService.updateSeat(seatId,newSeat));
    }

    @DeleteMapping("/{seatId}")
    public ResponseEntity<Void> deleteSeat(@PathVariable Long seatId){
        seatService.deleteById(seatId);
        return ResponseEntity.noContent().build();
    }

}
