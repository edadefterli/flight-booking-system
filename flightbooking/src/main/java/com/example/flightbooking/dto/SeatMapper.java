package com.example.flightbooking.dto;

import com.example.flightbooking.entities.Seat;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface SeatMapper {
    SeatMapper INSTANCE = Mappers.getMapper(SeatMapper.class);

    @Mapping(target = "flightId", source = "flight.id")
    SeatDto entityToDto(Seat seat);


    Seat dtoToEntity(SeatDto seatDto);
}
