package com.rideshare.payment.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rideshare.payment.entity.PaymentTransaction;
import com.rideshare.payment.service.PaymentTransactionService;
import com.rideshare.payment.util.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/payments")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class PaymentTransactionController {

    private final PaymentTransactionService paymentTransactionService;

    @PostMapping
    public ResponseEntity<ApiResponse<PaymentTransaction>> createTransaction(
            @RequestBody PaymentTransaction transaction) {
        log.info("Creating new payment transaction: {}", transaction);
        PaymentTransaction savedTransaction = paymentTransactionService.saveTransaction(transaction);
        return ResponseEntity.ok(ApiResponse.success(savedTransaction, "Transaction created successfully"));
    }

    @GetMapping("/rider/{riderId}")
    public ResponseEntity<ApiResponse<List<PaymentTransaction>>> getByRiderId(@PathVariable Long riderId) {
        log.info("Fetched transactions for riderId={}", riderId);
        List<PaymentTransaction> transactions = paymentTransactionService.getTransactionsByRiderId(riderId);
        return ResponseEntity.ok(ApiResponse.success(transactions, "Rider transaction fetched successfully"));
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<ApiResponse<List<PaymentTransaction>>> getByDriverId(@PathVariable Long driverId) {
        log.info("Fetching transactions for driverId={}", driverId);
        List<PaymentTransaction> transactions = paymentTransactionService.getTransactionsByDriverId(driverId);
        return ResponseEntity.ok(ApiResponse.success(transactions, "Driver transaction fetched successfully"));
    }

    @GetMapping("/trip/{tripId}")
    public ResponseEntity<ApiResponse<List<PaymentTransaction>>> getByTripId(@PathVariable Long tripId) {
        log.info("Fetching transactions for tripId={}", tripId);
        List<PaymentTransaction> transactions = paymentTransactionService.getTransactionsByTripId(tripId);
        return ResponseEntity.ok(ApiResponse.success(transactions, "Trip transactions feched successfully"));
    }
}
