package com.rideshare.driver.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vehicles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "driver_id", nullable = false, unique = true)
    private Drivers driver;

    @Column(name = "make", nullable = false, length = 50)
    private String make;

    @Column(name = "model", nullable = false, length = 50)
    private String model;

    @Column(name = "plate_number", nullable = false, unique = true, length = 20)
    private String PlateNumber;

    @Column(name = "color", length = 30)
    private String color;

    @Column(name = "year", length = 4)
    private String year;
}
