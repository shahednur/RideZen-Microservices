package com.rideshare.trip.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rideshare.trip.dto.TripRequest;
import com.rideshare.trip.dto.TripResponse;
import com.rideshare.trip.service.TripService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/trips")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class TripController {

    private final TripService tripService;

    @PostMapping
    public ResponseEntity<ApiResponse<TripResponse>> createTrip(@RequestBody TripRequest request) {
        TripResponse created = tripService.createTrip(request);
        return ResponseEntity.ok(
                ApiResponse.<TripResponse>builder()
                        .success(true)
                        .message("Trip created successfully")
                        .data(created)
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TripResponse>> getTripById(@PathVariable Long id) {
        TripResponse trip = tripService.getTripById(id);
        return ResponseEntity.ok(
                ApiResponse.<TripResponse>builder()
                        .success(true)
                        .message("Trip fetched successfully")
                        .data(trip)
                        .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TripResponse>>> getAllTrips() {
        List<TripResponse> trips = tripService.getAllTrips();
        return ResponseEntity.ok(
                ApiResponse.<List<TripResponse>>builder()
                        .success(true)
                        .message("All trips fetched successfully")
                        .data(trips)
                        .build());
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<ApiResponse<List<TripResponse>>> getTripsByDriver(@PathVariable Long driverId) {
        List<TripResponse> trips = tripService.getTripsByDriverId(driverId);
        return ResponseEntity.ok(
                ApiResponse.<List<TripResponse>>builder()
                        .success(true)
                        .message("Trips for driver fetched")
                        .data(trips)
                        .build());
    }

    @GetMapping("/passenger/{passengerId}")
    public ResponseEntity<ApiResponse<List<TripResponse>>> getTripsByPassenger(@PathVariable Long passengerId) {
        List<TripResponse> trips = tripService.getTripsByPassengerId(passengerId);
        return ResponseEntity.ok(
                ApiResponse.<List<TripResponse>>builder()
                        .success(true)
                        .message("Trips fetched for passenger")
                        .data(trips)
                        .build());
    }
}
