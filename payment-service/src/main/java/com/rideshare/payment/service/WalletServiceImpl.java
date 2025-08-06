package com.rideshare.payment.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rideshare.payment.entity.Wallet;
import com.rideshare.payment.repository.WalletRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Override
    public Optional<Wallet> getWalletByUserId(Long riderId) {
        return walletRepository.findByRiderId(riderId);
    }

    @Override
    @Transactional
    public Wallet updateWalletBalance(Long riderId, BigDecimal amount) {
        Wallet wallet = walletRepository.findByRiderId(riderId)
                .orElseThrow(() -> new RuntimeException("Wallet not found for userId: " + riderId));
        wallet.setBalance(wallet.getBalance().add(amount));
        log.info("Updated wallet for riderId: {}: {} ", riderId, wallet);
        return walletRepository.save(wallet);
    }

    @Override
    @Transactional
    public Wallet createWallet(Wallet wallet) {
        log.info("Creating new wallet: {}", wallet);
        return walletRepository.save(wallet);
    }

    @Override
    public BigDecimal getBalance(Long riderId) {
        return walletRepository.findByRiderId(riderId)
                .map(Wallet::getBalance)
                .orElse(BigDecimal.ZERO);
    }

    @Override
    @Transactional
    public Wallet credit(Long riderId, BigDecimal amount) {
        Wallet wallet = walletRepository.findByRiderId(riderId)
                .orElse(Wallet.builder().riderId(riderId).balance(BigDecimal.ZERO).build());
        wallet.setBalance(wallet.getBalance().add(amount));
        wallet.setUpdatedAt(LocalDateTime.now());
        return walletRepository.save(wallet);
    }

    @Override
    @Transactional
    public Wallet debit(Long riderId, BigDecimal amount) {
        Wallet wallet = walletRepository.findByRiderId(riderId)
                .orElseThrow(() -> new RuntimeException("Wallet not found for rider ID " + riderId));
        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        wallet.setBalance(wallet.getBalance().subtract(amount));
        wallet.setUpdatedAt(LocalDateTime.now());
        return walletRepository.save(wallet);
    }
}
