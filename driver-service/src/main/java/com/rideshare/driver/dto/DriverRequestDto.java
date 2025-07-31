package com.rideshare.driver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverRequestDto {

    private String name;
    private String phoneNumber;
    private String email;
    private String licenseNumber;
}
