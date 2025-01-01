package com.example.flightbooking.dto;

import com.example.flightbooking.entities.Passenger;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-29T11:05:55+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.25 (Sun Microsystems Inc.)"
)
@Component
public class PassengerMapperImpl implements PassengerMapper {

    @Override
    public PassengerDto entityToDto(Passenger passenger) {
        if ( passenger == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String surname = null;
        String email = null;

        id = passenger.getId();
        name = passenger.getName();
        surname = passenger.getSurname();
        email = passenger.getEmail();

        PassengerDto passengerDto = new PassengerDto( id, name, surname, email );

        return passengerDto;
    }

    @Override
    public Passenger dtoToEntity(PassengerDto passengerDto) {
        if ( passengerDto == null ) {
            return null;
        }

        Passenger passenger = new Passenger();

        passenger.setId( passengerDto.getId() );
        passenger.setName( passengerDto.getName() );
        passenger.setSurname( passengerDto.getSurname() );
        passenger.setEmail( passengerDto.getEmail() );

        return passenger;
    }
}
