package com.rideshare.passenger.service;

import java.util.List;
import java.util.Optional;

import com.rideshare.passenger.entity.Passenger;

public interface PassengerService {

    List<Passenger> getAllPassengers();

    Optional<Passenger> getPassengerById(Long id);

    Passenger createPassenger(Passenger passenger);

    void deletePassenger(Long id);
}
