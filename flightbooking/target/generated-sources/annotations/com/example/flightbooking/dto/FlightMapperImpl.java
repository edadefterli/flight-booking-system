package com.example.flightbooking.dto;

import com.example.flightbooking.entities.Flight;
import com.example.flightbooking.entities.Seat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-29T11:05:55+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.25 (Sun Microsystems Inc.)"
)
@Component
public class FlightMapperImpl implements FlightMapper {

    @Override
    public FlightDto entityToDto(Flight flight) {
        if ( flight == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String description = null;
        String departureAirport = null;
        String arrivalAirport = null;
        String airline = null;
        double price = 0.0d;
        LocalDateTime departureTime = null;
        LocalDateTime arrivalTime = null;
        List<SeatDto> seats = null;

        id = flight.getId();
        name = flight.getName();
        description = flight.getDescription();
        departureAirport = flight.getDepartureAirport();
        arrivalAirport = flight.getArrivalAirport();
        airline = flight.getAirline();
        price = flight.getPrice();
        departureTime = flight.getDepartureTime();
        arrivalTime = flight.getArrivalTime();
        seats = seatListToSeatDtoList( flight.getSeats() );

        FlightDto flightDto = new FlightDto( id, name, description, departureAirport, arrivalAirport, airline, price, departureTime, arrivalTime, seats );

        return flightDto;
    }

    @Override
    public Flight dtoToEntity(FlightDto flightDto) {
        if ( flightDto == null ) {
            return null;
        }

        Flight flight = new Flight();

        flight.setId( flightDto.getId() );
        flight.setName( flightDto.getName() );
        flight.setDescription( flightDto.getDescription() );
        flight.setDepartureAirport( flightDto.getDepartureAirport() );
        flight.setArrivalAirport( flightDto.getArrivalAirport() );
        flight.setAirline( flightDto.getAirline() );
        flight.setPrice( flightDto.getPrice() );
        flight.setDepartureTime( flightDto.getDepartureTime() );
        flight.setArrivalTime( flightDto.getArrivalTime() );
        flight.setSeats( seatDtoListToSeatList( flightDto.getSeats() ) );

        return flight;
    }

    protected SeatDto seatToSeatDto(Seat seat) {
        if ( seat == null ) {
            return null;
        }

        SeatDto seatDto = new SeatDto();

        seatDto.setId( seat.getId() );
        seatDto.setSeatNumber( seat.getSeatNumber() );
        seatDto.setAvailable( seat.isAvailable() );
        seatDto.setPrice( seat.getPrice() );

        return seatDto;
    }

    protected List<SeatDto> seatListToSeatDtoList(List<Seat> list) {
        if ( list == null ) {
            return null;
        }

        List<SeatDto> list1 = new ArrayList<SeatDto>( list.size() );
        for ( Seat seat : list ) {
            list1.add( seatToSeatDto( seat ) );
        }

        return list1;
    }

    protected Seat seatDtoToSeat(SeatDto seatDto) {
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

    protected List<Seat> seatDtoListToSeatList(List<SeatDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Seat> list1 = new ArrayList<Seat>( list.size() );
        for ( SeatDto seatDto : list ) {
            list1.add( seatDtoToSeat( seatDto ) );
        }

        return list1;
    }
}
