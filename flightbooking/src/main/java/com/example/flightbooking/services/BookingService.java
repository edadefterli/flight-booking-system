package com.example.flightbooking.services;

import com.example.flightbooking.entities.Passenger;
import com.example.flightbooking.entities.Payment;
import com.example.flightbooking.entities.Seat;
import com.example.flightbooking.enums.PaymentStatus;
import com.example.flightbooking.repositories.PassengerRepository;
import com.example.flightbooking.repositories.PaymentRepository;
import com.example.flightbooking.repositories.SeatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class BookingService {
    private SeatRepository seatRepository;
    private PaymentRepository paymentRepository;
    private PassengerRepository passengerRepository;

    public BookingService(SeatRepository seatRepository, PaymentRepository paymentRepository, PassengerRepository passengerRepository) {
        this.seatRepository = seatRepository;
        this.paymentRepository = paymentRepository;
        this.passengerRepository = passengerRepository;
    }

    @Transactional
    public void purchaseSeat(Long seatId, Long passengerId, double amount) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        if (!seat.isAvailable()) {
            throw new RuntimeException("Seat is already taken.");
        }

        if (amount < seat.getPrice()) {
            throw new RuntimeException("Insufficient payment amount. The amount must be greater than or equal to the seat price.");
        }

        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));

        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setStatus(PaymentStatus.PENDING);
        payment.setCreatedTime(LocalDateTime.now());
        payment.setSeat(seat);
        payment.setPassenger(passenger);
        passenger.getPayments().add(payment);
        passengerRepository.save(passenger);

        seat.setAvailable(false);
        seatRepository.save(seat);

        payment.setStatus(PaymentStatus.SUCCESS);
        paymentRepository.save(payment);
    }
}
