package com.example.demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "dateTime", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Calendar dateTime;

    @Column(name = "durationHours")
    private Integer duration;

    @OneToMany(mappedBy = "operation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Doctor> doctorList = new ArrayList<Doctor>();

    @OneToOne(mappedBy = "operationPatient")
    private Patient patient;

    @OneToOne(mappedBy = "operationRoom")
    private Room room;
}
