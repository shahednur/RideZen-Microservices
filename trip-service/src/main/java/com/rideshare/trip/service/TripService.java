package com.rideshare.trip.service;

import java.util.List;

import com.rideshare.trip.dto.TripRequest;
import com.rideshare.trip.dto.TripResponse;

public interface TripService {

    TripResponse createTrip(TripRequest request);

    TripResponse getTripById(Long id);

    List<TripResponse> getAllTrips();

    List<TripResponse> getTripsByDriverId(Long driverId);

    List<TripResponse> getTripsByPassengerId(Long passengerId);
}
