package com.rideshare.driver.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rideshare.driver.dto.DriverRequestDto;
import com.rideshare.driver.dto.DriverResponseDto;
import com.rideshare.driver.dto.UpdateDriverStatusRequestDto;
import com.rideshare.driver.dto.VehicleDto;
import com.rideshare.driver.service.DriverService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/drivers")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class DriverController {

    private final DriverService driverService;

    // Register Driver
    @PostMapping
    public ResponseEntity<DriverResponseDto> registerDriver(@RequestBody DriverRequestDto requestDto) {
        log.info("Registering new driver: {}", requestDto.getPhoneNumber());

        DriverResponseDto response = driverService.registerDriver(requestDto);
        return ResponseEntity.ok(response);
    }

    // Get Driver by id
    @GetMapping("/{driverId}")
    public ResponseEntity<DriverResponseDto> getDriverById(@PathVariable String driverId) {
        log.info("Fetching driver by id: {}", driverId);
        UUID uuid = UUID.fromString(formatUuid(driverId));
        DriverResponseDto response = driverService.getDriverById(uuid);
        return ResponseEntity.ok(response);
    }

    // Update Driver status
    @PutMapping("/{driverId}/status")
    public ResponseEntity<DriverResponseDto> updateDriverStatus(@PathVariable UUID driverId,
            @RequestBody UpdateDriverStatusRequestDto statusRequestDto) {
        log.info("Updating status for driver: {} to {}", driverId, statusRequestDto.getStatus());
        DriverResponseDto response = driverService.updateDriverStatus(driverId, statusRequestDto);
        return ResponseEntity.ok(response);
    }

    // Add or Update Vehicle for Driver
    @PostMapping("/{driverId}/vehicle")
    public ResponseEntity<DriverResponseDto> addOrUpdateVehicle(@PathVariable UUID driverId,
            @RequestBody VehicleDto vehicleDto) {
        log.info("Adding/updating vehicle for driver: {}", driverId);
        DriverResponseDto response = driverService.addOrUpdateVehicle(driverId, vehicleDto);
        return ResponseEntity.ok(response);
    }

    private String formatUuid(String raw) {
        if (raw.length() == 32) {
            return raw.replaceFirst(
                    "(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})",
                    "$1-$2-$3-$4-$5");
        }
        return raw;
    }
}
