package com.rideshare.passenger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PassengerController {

    @GetMapping("/")
    public String healthCheck() {
        return "passenger-service is running";
    }
}
