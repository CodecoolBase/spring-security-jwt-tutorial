package com.example.demo.repository;

import com.example.demo.model.VehicleAppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<VehicleAppUser, Long> {

    Optional<VehicleAppUser> findByUsername(String username);

}
