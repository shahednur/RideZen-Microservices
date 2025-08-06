package com.rideshare.payment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rideshare.payment.entity.Fare;

@Repository
public interface FareRepository extends JpaRepository<Fare, Long> {

    Optional<Fare> findByTripId(Long tripId);
}
