package com.example.flightbooking.dto;


import com.example.flightbooking.api.requests.FlightRequest;
import com.example.flightbooking.entities.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FlightMapper {
    FlightMapper INSTANCE = Mappers.getMapper(FlightMapper.class);
    FlightDto entityToDto(Flight flight);
    Flight dtoToEntity(FlightDto flightDto);
}
