package com.rideshare.payment.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rideshare.payment.entity.Fare;
import com.rideshare.payment.repository.FareRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FareServiceImpl implements FareService {

    private final FareRepository fareRepository;
    private static final double PER_KM_RATE = 15.0;
    private static final double PER_MIN_RATE = 2.0;

    @Override
    public Optional<Fare> getFareByTripId(Long tripId) {
        return fareRepository.findByTripId(tripId);
    }

    @Override
    public Fare saveFare(Fare fare) {
        return fareRepository.save(fare);
    }

    @Override
    public double calculateFare(double baseFare, double distanceKm, double durationMin) {
        double distanceCharge = distanceKm * PER_KM_RATE;
        double timeCharge = durationMin * PER_MIN_RATE;
        double totalFare = baseFare + distanceCharge + timeCharge;
        log.debug("Fare breakdown -> Base: {}, Distance: {}, Time: {}, Total: {}", baseFare, distanceCharge, timeCharge,
                totalFare);
        return totalFare;
    }
}
