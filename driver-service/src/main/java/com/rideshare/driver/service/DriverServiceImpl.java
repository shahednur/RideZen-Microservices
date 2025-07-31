package com.rideshare.driver.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.rideshare.driver.dto.DriverRequestDto;
import com.rideshare.driver.dto.DriverResponseDto;
import com.rideshare.driver.dto.UpdateDriverStatusRequestDto;
import com.rideshare.driver.dto.VehicleDto;
import com.rideshare.driver.entity.Drivers;
import com.rideshare.driver.entity.DriverStatus;
import com.rideshare.driver.entity.Vehicle;
import com.rideshare.driver.repository.DriverRepository;
import com.rideshare.driver.repository.VehicleRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;

    @Override
    public DriverResponseDto registerDriver(DriverRequestDto dto) {
        if (driverRepository.existsByPhoneNumber(dto.getPhoneNumber())) {
            throw new RuntimeException("Driver already exists with this phone number");
        }

        if (driverRepository.existsByLicenseNumber(dto.getLicenseNumber())) {
            throw new RuntimeException("License already registered");
        }

        Drivers driver = Drivers.builder()
                .name(dto.getName())
                .phoneNumber(dto.getPhoneNumber())
                .email(dto.getEmail())
                .licenseNumber(dto.getLicenseNumber())
                .status(DriverStatus.OFFLINE)
                .build();
        driverRepository.save(driver);

        return mapToResponseDto(driver);
    }

    @Override
    public DriverResponseDto getDriverById(UUID driverId) {
        Drivers driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        return mapToResponseDto(driver);
    }

    @Override
    public DriverResponseDto updateDriverStatus(UUID driverId, UpdateDriverStatusRequestDto requestDto) {
        Drivers driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver id not found"));

        driver.setStatus(requestDto.getStatus());
        driverRepository.save(driver);
        return mapToResponseDto(driver);
    }

    @Override
    public DriverResponseDto addOrUpdateVehicle(UUID driverId, VehicleDto vehicleDto) {
        Drivers driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found"));
        if (vehicleRepository.existsByPlateNumber(vehicleDto.getPlateNumber())) {
            throw new RuntimeException("Vehicle with this plate number doesn't exist");
        }

        Vehicle vehicle = vehicleRepository.findByDriver(driverId).orElse(null);

        if (vehicle == null) {
            vehicle = Vehicle.builder()
                    .driver(driver)
                    .build();
        }

        vehicle.setMake(vehicleDto.getMake());
        vehicle.setModel(vehicle.getModel());
        vehicle.setPlateNumber(vehicle.getPlateNumber());
        vehicle.setColor(vehicle.getColor());
        vehicle.setYear(vehicle.getYear());

        vehicleRepository.save(vehicle);
        driver.setVehicle(vehicle);
        return mapToResponseDto(driver);
    }

    private DriverResponseDto mapToResponseDto(Drivers driver) {
        Vehicle vehicle = driver.getVehicle();

        VehicleDto vehicleDto = null;
        if (vehicle != null) {
            vehicleDto = VehicleDto.builder()
                    .make(vehicle.getMake())
                    .model(vehicle.getModel())
                    .plateNumber(vehicle.getPlateNumber())
                    .color(vehicle.getColor())
                    .year(vehicle.getYear())
                    .build();
        }

        return DriverResponseDto.builder()
                .id(driver.getId())
                .name(driver.getName())
                .phoneNumber(driver.getPhoneNumber())
                .email(driver.getEmail())
                .licenseNumber(driver.getLicenseNumber())
                .status(driver.getStatus())
                .vehicle(vehicleDto)
                .build();
    }
}
