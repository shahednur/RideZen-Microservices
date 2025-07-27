package com.rideshare.trip.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "trips")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "driver_id", nullable = false)
    private Long driverId;
    @Column(name = "passenger_id", nullable = false)
    private Long passengerId;
    @Column(name = "start_location", nullable = false)
    private String startLocation;
    @Column(name = "end_location", nullable = false)
    private String endLocation;
    @Column(name = "fare", nullable = false, precision = 10, scale = 2)
    private BigDecimal fare;
    @Column(name = "trip_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TripStatus tripStatus;
    @Column(name = "start_time")
    private LocalDateTime startTime;
    @Column(name = "end_time")
    private LocalDateTime endTime;

}
