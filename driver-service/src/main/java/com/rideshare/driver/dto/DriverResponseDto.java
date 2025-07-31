package com.rideshare.driver.dto;

import java.util.UUID;

import com.rideshare.driver.entity.DriverStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverResponseDto {
    private UUID id;
    private String name;
    private String phoneNumber;
    private String email;
    private String licenseNumber;
    private DriverStatus status;
    private VehicleDto vehicle;
}
