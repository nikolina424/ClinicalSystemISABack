package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Clinic {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    private String description;
    private String address;

    @ManyToOne(fetch = FetchType.EAGER)
    private ClinicCenter clinicCenter;

    @ManyToOne(fetch = FetchType.EAGER)
    private User admin;
}
