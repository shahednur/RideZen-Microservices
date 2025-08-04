package com.rideshare.passenger.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rideshare.passenger.entity.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
