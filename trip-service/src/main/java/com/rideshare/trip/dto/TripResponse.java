package com.rideshare.trip.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.rideshare.trip.entity.TripStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripResponse {

    private Long id;
    private Long driverId;
    private Long passengerId;
    private String origin;
    private String destination;
    private BigDecimal fare;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private TripStatus tripStatus;
}
