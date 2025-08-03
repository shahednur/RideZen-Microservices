package com.rideshare.driver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.rideshare.driver.repository")
public class DriverOrchestrationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DriverOrchestrationServiceApplication.class, args);
	}

}
