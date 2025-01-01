package com.example.flightbooking.services;


import com.example.flightbooking.api.requests.FlightRequest;
import com.example.flightbooking.api.responses.FlightResponse;
import com.example.flightbooking.dto.FlightDto;
import com.example.flightbooking.dto.FlightMapper;
import com.example.flightbooking.dto.SeatMapper;
import com.example.flightbooking.entities.Flight;
import com.example.flightbooking.repositories.FlightRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightService {

    private FlightRepository flightRepository;
    private FlightMapper flightMapper;
    private SeatMapper seatMapper;
    public FlightService(FlightRepository flightRepository, FlightMapper flightMapper, SeatMapper seatMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
        this.seatMapper = seatMapper;
    }

    public List<FlightResponse> getAllFlights(){
        return flightRepository.findAll().stream().map(flight -> {
            FlightResponse response = new FlightResponse(flight);
            return response;
        }).collect(Collectors.toList());
    }

    public FlightResponse getFlightById(Long flightId) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(()-> new RuntimeException("Flight not found with id: " + flightId));
        return new FlightResponse(flight);
    }

    public FlightDto createFlight(FlightRequest flightRequest){
        Flight flight = prepareFlight(flightRequest);
        Flight savedFlight = flightRepository.save(flight);
        return flightMapper.entityToDto(savedFlight);
    }

    public void deleteById(Long flightId){
        if(!flightRepository.existsById(flightId)){
            throw new RuntimeException("Flight not found with id: "+flightId);
        }
        flightRepository.deleteById(flightId);
    }

    public FlightDto updateFlight(Long flightId, FlightRequest flightRequest){
        Flight existingFlight = flightRepository.findById(flightId)
                .orElseThrow(()-> new RuntimeException("Flight not found with id: "+ flightId));
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(flightRequest,existingFlight);
        return flightMapper.entityToDto(flightRepository.save(existingFlight));
    }

    private Flight prepareFlight(FlightRequest flightRequest){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Flight flight = modelMapper.map(flightRequest, Flight.class);
        return flight;
    }
}
