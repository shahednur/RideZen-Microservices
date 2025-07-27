package com.rideshare.trip.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rideshare.trip.dto.TripRequest;
import com.rideshare.trip.dto.TripResponse;
import com.rideshare.trip.entity.Trip;
import com.rideshare.trip.repository.TripRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;

    @Override
    @Transactional
    public TripResponse createTrip(TripRequest request) {
        Trip trip = Trip.builder()
                .driverId(request.getDriverId())
                .passengerId(request.getPassengerId())
                .startLocation(request.getOrigin())
                .endLocation(request.getDestination())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .tripStatus(request.getTripStatus())
                .build();
        Trip saved = tripRepository.save(trip);
        return toResponse(saved);
    }

    @Override
    public TripResponse getTripById(Long id) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip not found with id: " + id));
        return toResponse(trip);
    }

    @Override
    public List<TripResponse> getAllTrips() {
        return tripRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<TripResponse> getTripsByDriverId(Long driverId) {
        return tripRepository.findByDriverId(driverId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<TripResponse> getTripsByPassengerId(Long passengerId) {
        return tripRepository.findByPassengerId(passengerId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private TripResponse toResponse(Trip trip) {
        return TripResponse.builder()
                .id(trip.getId())
                .driverId(trip.getDriverId())
                .passengerId(trip.getPassengerId())
                .origin(trip.getStartLocation())
                .destination(trip.getEndLocation())
                .fare(trip.getFare())
                .startTime(trip.getStartTime())
                .endTime(trip.getEndTime())
                .tripStatus(trip.getTripStatus())
                .build();
    }

}
