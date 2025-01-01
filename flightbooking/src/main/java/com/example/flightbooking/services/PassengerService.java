package com.example.flightbooking.services;

import com.example.flightbooking.api.requests.PassengerRequest;
import com.example.flightbooking.dto.PassengerDto;
import com.example.flightbooking.dto.PassengerMapper;
import com.example.flightbooking.entities.Passenger;
import com.example.flightbooking.repositories.PassengerRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {
    private PassengerRepository passengerRepository;
    private PassengerMapper passengerMapper;


    public PassengerService(PassengerRepository passengerRepository, PassengerMapper passengerMapper) {
        this.passengerRepository = passengerRepository;
        this.passengerMapper = passengerMapper;
    }

    public List<Passenger> getAllPassengers(){
        return passengerRepository.findAll();
    }

    public PassengerDto createPassenger(PassengerRequest passengerRequest) {
        Passenger passenger = preparePassenger(passengerRequest);
        Passenger savedPassenger = passengerRepository.save(passenger);
        return passengerMapper.entityToDto(savedPassenger);
    }

    public Passenger getById(Long passengerId) {
        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(()->new RuntimeException("Passenger not found."));
        return passenger;
    }

    public void deleteById(Long passengerId) {
        passengerRepository.deleteById(passengerId);
    }

    private Passenger preparePassenger(PassengerRequest passengerRequest){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Passenger passenger = modelMapper.map(passengerRequest,Passenger.class);
        return passenger;
    }
}
