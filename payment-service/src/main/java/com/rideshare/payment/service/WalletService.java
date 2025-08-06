package com.rideshare.payment.service;

import java.math.BigDecimal;
import java.util.Optional;

import com.rideshare.payment.entity.Wallet;

public interface WalletService {

    Optional<Wallet> getWalletByUserId(Long userId);

    Wallet updateWalletBalance(Long userId, BigDecimal amount);

    Wallet createWallet(Wallet wallet);

    BigDecimal getBalance(Long riderId);

    Wallet credit(Long riderId, BigDecimal amount);

    Wallet debit(Long riderId, BigDecimal amount);
}
