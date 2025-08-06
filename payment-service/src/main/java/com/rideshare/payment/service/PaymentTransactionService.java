package com.rideshare.payment.service;

import java.util.List;

import com.rideshare.payment.entity.PaymentTransaction;

public interface PaymentTransactionService {

    PaymentTransaction saveTransaction(PaymentTransaction transaction);

    List<PaymentTransaction> getTransactionsByDriverId(Long driverId);

    List<PaymentTransaction> getTransactionsByRiderId(Long riderId);

    List<PaymentTransaction> getTransactionsByTripId(Long tripId);
}
