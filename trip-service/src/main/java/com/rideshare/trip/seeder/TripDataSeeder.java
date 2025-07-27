package com.rideshare.trip.seeder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.rideshare.trip.entity.Trip;
import com.rideshare.trip.entity.TripStatus;
import com.rideshare.trip.repository.TripRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@Profile({ "local", "test" })
@RequiredArgsConstructor
public class TripDataSeeder implements CommandLineRunner {

    private final TripRepository tripRepository;
    private final Faker faker = new Faker();

    private static final TripStatus[] STATUS_FLOW = {
            TripStatus.REQUESTED,
            TripStatus.DRIVER_ASSIGNED,
            TripStatus.IN_PROGRESS,
            TripStatus.COMPLETED,
            TripStatus.RIDE_ENDED
    };

    @Override
    @Transactional
    public void run(String... args) {
        if (tripRepository.count() == 0) {
            List<Trip> trips = IntStream.rangeClosed(1, 100)
                    .mapToObj(this::generateFakeTrip)
                    .collect(Collectors.toList());

            tripRepository.saveAll(trips);
            // logger.info("Seeded {} trip records", trips.size());
        }
    }

    private Trip generateFakeTrip(int index) {
        Trip trip = new Trip();
        trip.setDriverId(1000L + index % 20); // 20 unique drivers
        trip.setPassengerId(2000L + index % 50); // 50 unique passengers

        trip.setStartLocation(faker.address().fullAddress());
        trip.setEndLocation(faker.address().fullAddress());

        // Generate realistic fare (base + distance + surge)
        double baseFare = 2.50;
        double distanceKm = 1 + faker.random().nextDouble() * 24;
        double distanceRate = 1.25 * distanceKm;
        double surgeMultiplier = 1.0 + faker.random().nextDouble() * 1.5;

        double totalFare = (baseFare + distanceRate) * surgeMultiplier;

        trip.setFare(BigDecimal.valueOf(totalFare).setScale(2, RoundingMode.HALF_UP));

        LocalDateTime now = LocalDateTime.now();
        trip.setStartTime(now.plusMinutes(faker.random().nextInt(15, 120)));
        trip.setEndTime(trip.getStartTime().plusMinutes(faker.random().nextInt(15, 60)));
        trip.setTripStatus(getRandomStatus());

        int statusIndex = index % STATUS_FLOW.length;
        trip.setTripStatus(STATUS_FLOW[statusIndex]);
        LocalDateTime nows = LocalDateTime.now();
        switch (trip.getTripStatus()) {
            case REQUESTED:
                trip.setStartTime(nows.plusHours(1));
                break;
            case DRIVER_ASSIGNED:
                trip.setStartTime(nows.plusMinutes(30));
                break;
            case IN_PROGRESS:
                trip.setStartTime(nows.minusMinutes(15));
                trip.setEndTime(nows.plusMinutes(15));
                break;
            case COMPLETED:
                trip.setStartTime(nows.minusHours(1));
                trip.setEndTime(nows.minusMinutes(30));
                break;
            default:
                break;
        }
        return trip;
    }

    private TripStatus getRandomStatus() {
        TripStatus[] statuses = TripStatus.values();
        return statuses[faker.random().nextInt(statuses.length)];
    }
}