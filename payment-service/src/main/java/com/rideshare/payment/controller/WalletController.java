package com.rideshare.payment.controller;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rideshare.payment.entity.Wallet;
import com.rideshare.payment.service.WalletService;
import com.rideshare.payment.util.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/wallets")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class WalletController {

    private final WalletService walletService;

    @GetMapping("/balance/{riderId}")
    public ResponseEntity<ApiResponse<BigDecimal>> getBalance(@PathVariable Long riderId) {
        log.info("Fetching balance for riderId={}", riderId);
        BigDecimal balance = walletService.getBalance(riderId);
        return ResponseEntity.ok(ApiResponse.success(balance, "Balance fetched successfully"));
    }

    @PostMapping("/credit")
    public ResponseEntity<ApiResponse<Wallet>> credit(@RequestParam Long riderId, @RequestParam BigDecimal amount) {
        log.info("Creding amount={} to riderId={}", amount, riderId);
        Wallet updatedWallet = walletService.credit(riderId, amount);
        return ResponseEntity.ok(ApiResponse.success(updatedWallet, "Wallet credited successfully"));
    }

    @PostMapping("/debit")
    public ResponseEntity<ApiResponse<Wallet>> debit(@RequestParam Long riderId, @RequestParam BigDecimal amount) {
        log.info("Debiting amount={} from riderId={}", amount, riderId);
        Wallet updatedWallet = walletService.debit(riderId, amount);
        return ResponseEntity.ok(ApiResponse.success(updatedWallet, "Wallet debited successfully"));
    }
}
