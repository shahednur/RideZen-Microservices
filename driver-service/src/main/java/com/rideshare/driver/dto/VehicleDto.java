package com.rideshare.driver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleDto {

    private String make;
    private String model;
    private String plateNumber;
    private String color;
    private String year;
}
