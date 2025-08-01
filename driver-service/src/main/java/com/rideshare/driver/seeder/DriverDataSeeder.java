package com.rideshare.driver.seeder;

import java.util.Random;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.rideshare.driver.entity.DriverStatus;
import com.rideshare.driver.entity.Drivers;
import com.rideshare.driver.entity.Vehicle;
import com.rideshare.driver.repository.DriverRepository;
import com.rideshare.driver.repository.VehicleRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class DriverDataSeeder implements CommandLineRunner {

    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;
    private final Random random = new Random();

    @Override
    @Transactional
    public void run(String... args) {
        if (driverRepository.count() > 0 || vehicleRepository.count() > 0) {
            log.info("Driver or Vehicle data already exists. Skipping seeding");
            return;
        }

        log.info("Seeding 100 sample drivers with vehicles...");

        IntStream.rangeClosed(1, 100).forEach(i -> {
            // Create driver first
            Drivers driver = Drivers.builder()
                    .name("Driver " + i)
                    .email("driver" + i + "@example.com")
                    .phoneNumber("+88017" + String.format("%08d", i)) // Simple sequential phone numbers
                    .licenseNumber("LIC" + String.format("%06d", i)) // Simple sequential license numbers
                    .status(i % 3 == 0 ? DriverStatus.OFFLINE : DriverStatus.ONLINE) // Mix of online/offline
                    .build();

            // Create vehicle with proper data sizes
            Vehicle vehicle = Vehicle.builder()
                    .plateNumber("DH-" + String.format("%06d", i)) // Dhaka format
                    .model("Model" + (i % 5 + 1)) // Model1 to Model5
                    .make(getMake(i))
                    .color(getRandomColor())
                    .year(2015 + (i % 9)) // Years from 2015 to 2023
                    .build();

            // Set up bidirectional relationship properly
            vehicle.setDriver(driver);
            driver.setVehicle(vehicle);

            try {
                // Save the owning side (driver) - cascade will handle vehicle
                driverRepository.save(driver);

                if (i % 20 == 0) {
                    log.info("Seeded {} drivers", i);
                }
            } catch (Exception e) {
                log.error("Error seeding driver {}: {}", i, e.getMessage());
            }
        });

        log.info("Successfully seeded 100 drivers with vehicles");
    }

    private String getMake(int index) {
        String[] makes = { "Toyota", "Honda", "Nissan", "Hyundai", "Suzuki" };
        return makes[index % makes.length];
    }

    private String getRandomColor() {
        String[] colors = { "White", "Black", "Silver", "Blue", "Red" };
        return colors[random.nextInt(colors.length)];
    }
}
