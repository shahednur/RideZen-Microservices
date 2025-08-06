package com.rideshare.payment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rideshare.payment.entity.PaymentTransaction;
import com.rideshare.payment.repository.PaymentTransactionRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentTransactionServiceImpl implements PaymentTransactionService {

    private final PaymentTransactionRepository transactionRepository;

    @Override
    @Transactional
    public PaymentTransaction saveTransaction(PaymentTransaction transaction) {
        log.info("Saving transaction: {}", transaction);
        return transactionRepository.save(transaction);
    }

    @Override
    public List<PaymentTransaction> getTransactionsByRiderId(Long riderId) {
        return transactionRepository.findByRiderId(riderId);
    }

    @Override
    public List<PaymentTransaction> getTransactionsByDriverId(Long driverId) {
        return transactionRepository.findByDriverId(driverId);
    }

    @Override
    public List<PaymentTransaction> getTransactionsByTripId(Long tripId) {
        return transactionRepository.findByTripId(tripId);
    }
}
