package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleAppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    // roles of the user (ADMIN, USER,..)
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();
}
