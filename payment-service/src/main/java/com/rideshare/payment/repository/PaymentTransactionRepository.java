package com.rideshare.payment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rideshare.payment.entity.PaymentTransaction;

@Repository
public interface PaymentTransactionRepository extends JpaRepository<PaymentTransaction, Long> {

    List<PaymentTransaction> findByRiderId(Long passengerId);

    List<PaymentTransaction> findByDriverId(Long driverId);

    List<PaymentTransaction> findByTripId(Long tripId);
}
