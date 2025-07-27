package com.rideshare.trip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rideshare.trip.entity.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    List<Trip> findByDriverId(Long driverId);

    List<Trip> findByPassengerId(Long passengerId);

    List<Trip> findByTripStatus(String tripStatus);
}
