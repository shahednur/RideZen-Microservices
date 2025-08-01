package com.rideshare.driver.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rideshare.driver.entity.Drivers;
import com.rideshare.driver.entity.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {

    Optional<Vehicle> findByPlateNumber(String plateNumber);

    Optional<Vehicle> findByDriver(Drivers driverId);

    boolean existsByPlateNumber(String plateNumber);

}
