package com.example.flightbooking.api.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class PassengerRequest {
    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String email;
}
