package com.example.flightbooking.services;

import com.example.flightbooking.api.requests.SeatRequest;
import com.example.flightbooking.dto.SeatDto;
import com.example.flightbooking.dto.SeatMapper;
import com.example.flightbooking.entities.Flight;
import com.example.flightbooking.entities.Seat;
import com.example.flightbooking.repositories.FlightRepository;
import com.example.flightbooking.repositories.SeatRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatService {
    private SeatRepository seatRepository;
    private FlightRepository flightRepository;
    private SeatMapper seatMapper;

    public SeatService(SeatRepository seatRepository,FlightRepository flightRepository, SeatMapper seatMapper) {
        this.seatRepository = seatRepository;
        this.flightRepository = flightRepository;
        this.seatMapper = seatMapper;
    }

    public List<SeatDto> getAllSeats(){
        return seatRepository.findAll()
                .stream()
                .map(seatMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public SeatDto createSeat(SeatRequest seatRequest) {
        Seat seat = prepareSeat(seatRequest);
        Long flightId = seatRequest.getFlightId();
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(()-> new IllegalArgumentException("No flight found for the entered id."));

        seat.setFlight(flight);
        Seat savedSeat = seatRepository.save(seat);

        flight.getSeats().add(savedSeat);
        flightRepository.save(flight);

        return seatMapper.entityToDto(savedSeat);
    }

    public void deleteById(Long seatId) {
        if(!seatRepository.existsById(seatId)){
            throw new RuntimeException("Seat not found with id: "+seatId);
        }
        seatRepository.deleteById(seatId);
    }

    public SeatDto updateSeat(Long seatId, SeatRequest seatRequest){
        Seat existingSeat = seatRepository.findById(seatId)
                .orElseThrow(()->new RuntimeException("Seat not found with id: "+seatId));

        Long flightId = seatRequest.getFlightId();
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(()-> new IllegalArgumentException("No flight found for the entered id."));

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(seatRequest,existingSeat);

        flight.getSeats().add(existingSeat);
        flightRepository.save(flight);
        return seatMapper.entityToDto(seatRepository.save(existingSeat));
    }

    private Seat prepareSeat(SeatRequest seatRequest){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Seat seat = modelMapper.map(seatRequest, Seat.class);
        return seat;
    }
}
