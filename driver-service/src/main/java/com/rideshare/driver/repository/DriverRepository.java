package com.rideshare.driver.repository;

import com.rideshare.driver.entity.Drivers;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Drivers, UUID> {

    Optional<Drivers> findByPhoneNumber(String phoneNumber);

    Optional<Drivers> findByLicenseNumber(String licenseNumber);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByLicenseNumber(String licenseNumber);
}
