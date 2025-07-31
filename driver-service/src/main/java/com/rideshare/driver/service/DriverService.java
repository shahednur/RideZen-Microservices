package com.rideshare.driver.service;

import java.util.UUID;

import com.rideshare.driver.dto.DriverRequestDto;
import com.rideshare.driver.dto.DriverResponseDto;
import com.rideshare.driver.dto.UpdateDriverStatusRequestDto;
import com.rideshare.driver.dto.VehicleDto;

public interface DriverService {

    DriverResponseDto registerDriver(DriverRequestDto request);

    DriverResponseDto getDriverById(UUID id);

    DriverResponseDto updateDriverStatus(UUID driverId, UpdateDriverStatusRequestDto statusDto);

    DriverResponseDto addOrUpdateVehicle(UUID driverId, VehicleDto vehicleDto);
}
