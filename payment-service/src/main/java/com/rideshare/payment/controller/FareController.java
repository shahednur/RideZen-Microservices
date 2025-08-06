package com.rideshare.payment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rideshare.payment.service.FareService;
import com.rideshare.payment.util.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/fares")
@CrossOrigin(origins = "*")
@Slf4j
@RequiredArgsConstructor
public class FareController {

    private final FareService fareService;

    @GetMapping("/calculate")
    public ResponseEntity<ApiResponse<Double>> calculateFare(
            @RequestParam double baseFare,
            @RequestParam double distanceKm,
            @RequestParam double durationMin) {
        log.info("Calculating fare for distance={} km, duration={} min, baseFare={}", distanceKm, durationMin,
                baseFare);
        double fare = fareService.calculateFare(baseFare, distanceKm, durationMin);
        return ResponseEntity.ok(ApiResponse.success(fare, "Fare calculated successfully"));
    }
}
