package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.model.Vehicle;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VehicleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final VehicleRepository vehicles;

    private final UserRepository users;

    public DataInitializer(VehicleRepository vehicles, UserRepository users) {
        this.vehicles = vehicles;
        this.users = users;
    }

    @Override
    public void run(String... args) {
        log.debug("initializing sample data...");
        Arrays.asList("motor", "car", "truck").forEach(v -> this.vehicles.saveAndFlush(Vehicle.builder().name(v).build()));

        log.debug("printing all vehicles...");
        vehicles.findAll().forEach(v -> log.debug(" Vehicle :" + v.toString()));

        users.save(User.builder()
            .username("user")
            .password("password")
            .roles(Arrays.asList("ROLE_USER"))
            .build()
        );

        users.save(User.builder()
            .username("admin")
            .password("password")
            .roles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"))
            .build()
        );
        log.debug("printing all users...");
        users.findAll().forEach(v -> log.debug(" User :" + v.toString()));
    }
}
