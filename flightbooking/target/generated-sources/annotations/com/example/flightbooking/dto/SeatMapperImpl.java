package com.example.flightbooking.dto;

import com.example.flightbooking.entities.Flight;
import com.example.flightbooking.entities.Seat;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-29T11:18:05+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.25 (Sun Microsystems Inc.)"
)
@Component
public class SeatMapperImpl implements SeatMapper {

    @Override
    public SeatDto entityToDto(Seat seat) {
        if ( seat == null ) {
            return null;
        }

        SeatDto seatDto = new SeatDto();

        seatDto.setFlightId( seatFlightId( seat ) );
        seatDto.setId( seat.getId() );
        seatDto.setSeatNumber( seat.getSeatNumber() );
        seatDto.setAvailable( seat.isAvailable() );
        seatDto.setPrice( seat.getPrice() );

        return seatDto;
    }

    @Override
    public Seat dtoToEntity(SeatDto seatDto) {
        if ( seatDto == null ) {
            return null;
        }

        Seat seat = new Seat();

        seat.setId( seatDto.getId() );
        seat.setSeatNumber( seatDto.getSeatNumber() );
        seat.setAvailable( seatDto.isAvailable() );
        seat.setPrice( seatDto.getPrice() );

        return seat;
    }

    private Long seatFlightId(Seat seat) {
        if ( seat == null ) {
            return null;
        }
        Flight flight = seat.getFlight();
        if ( flight == null ) {
            return null;
        }
        Long id = flight.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
