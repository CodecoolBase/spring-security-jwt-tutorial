package com.example.demo.controller;

import com.example.demo.model.VehicleAppUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class UserinfoController {

    @GetMapping("/me")
    public String currentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        VehicleAppUser user = (VehicleAppUser) authentication.getPrincipal();
        return user.getUsername() + "\n" + user.getRoles();
    }
}