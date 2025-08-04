package com.rideshare.passenger;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.rideshare.passenger.entity.Passenger;
import com.rideshare.passenger.repository.PassengerRepository;

@SpringBootApplication
public class PassengerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PassengerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner testPassengerRepo(PassengerRepository repo) {
		return args -> {
			Passenger p = Passenger.builder().name("Shahed").build();
			repo.findAll().forEach(passenger -> System.out.println("DB RECORD: " + passenger));
		};
	}

}
