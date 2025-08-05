package com.rideshare.passenger.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rideshare.passenger.entity.Passenger;
import com.rideshare.passenger.service.PassengerService;
import com.rideshare.passenger.util.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/passengers")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class PassengerController {

    private final PassengerService passengerService;

    @GetMapping("/")
    public String healthCheck() {
        return "passenger-service is running";
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Passenger>>> getAllPassengers() {
        List<Passenger> passengers = passengerService.getAllPassengers();
        return ResponseEntity.ok(ApiResponse.success(passengers));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Passenger>> getPassengerById(@PathVariable Long id) {
        return passengerService.getPassengerById(id)
                .map(p -> ResponseEntity.ok(ApiResponse.success(p)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Passenger>> createPassenger(@RequestBody Passenger passenger) {
        Passenger created = passengerService.createPassenger(passenger);
        return ResponseEntity.ok(ApiResponse.success(created));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deletePassenger(@PathVariable Long id) {
        passengerService.deletePassenger(id);
        return ResponseEntity.ok(ApiResponse.success("Passenger deleted"));
    }
}
