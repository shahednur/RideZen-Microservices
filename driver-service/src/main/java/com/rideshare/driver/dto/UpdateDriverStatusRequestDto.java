package com.rideshare.driver.dto;

import com.rideshare.driver.entity.DriverStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateDriverStatusRequestDto {
    private DriverStatus status;
}
