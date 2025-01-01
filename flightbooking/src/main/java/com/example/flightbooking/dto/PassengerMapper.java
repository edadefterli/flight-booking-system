package com.example.flightbooking.dto;

import com.example.flightbooking.entities.Passenger;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PassengerMapper {
    PassengerMapper INSTANCE = Mappers.getMapper(PassengerMapper.class);
    PassengerDto entityToDto(Passenger passenger);
    Passenger dtoToEntity(PassengerDto passengerDto);
}
