package com.example.demo;

import com.example.demo.model.VehicleAppUser;
import com.example.demo.model.Vehicle;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VehicleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final VehicleRepository vehicles;

    private final UserRepository users;

    private final PasswordEncoder passwordEncoder;

    public DataInitializer(VehicleRepository vehicles, UserRepository users) {
        this.vehicles = vehicles;
        this.users = users;
        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public void run(String... args) {
        log.debug("initializing sample data...");
        Arrays.asList("motor", "car", "truck").forEach(v -> this.vehicles.saveAndFlush(Vehicle.builder().name(v).build()));

        log.debug("printing all vehicles...");
        vehicles.findAll().forEach(v -> log.debug(" Vehicle :" + v.toString()));

        users.save(VehicleAppUser.builder()
            .username("user")
            .password(passwordEncoder.encode("password"))
            .roles(Arrays.asList("ROLE_USER"))
            .build()
        );

        users.save(VehicleAppUser.builder()
            .username("admin")
            .password(passwordEncoder.encode("password"))
            .roles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"))
            .build()
        );

        log.debug("printing all users...");
        users.findAll().forEach(v -> log.debug(" VehicleAppUser :" + v.toString()));
    }
}
