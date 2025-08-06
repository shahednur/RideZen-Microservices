package com.rideshare.payment.service;

import java.util.Optional;

import com.rideshare.payment.entity.Fare;

public interface FareService {

    Optional<Fare> getFareByTripId(Long tripId);

    Fare saveFare(Fare fare);

    double calculateFare(double baseFare, double distanceKm, double durationMin);
}
