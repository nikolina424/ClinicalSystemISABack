package com.example.demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Clinic {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ClinicCenter clinicCenter;

    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Doctor> doctorList = new ArrayList<>();

    @OneToMany(mappedBy = "clinicM", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MedicalSister> medicalSisterList = new ArrayList<>();

    @OneToMany(mappedBy = "clinicA", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AdminClinic> adminClinicList = new ArrayList<>();

    public Clinic() {
    }

    public Clinic(Long id, String name, ClinicCenter clinicCenter) {
        this.id = id;
        this.name = name;
        this.clinicCenter = clinicCenter;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClinicCenter getClinicCenter() {
        return clinicCenter;
    }

    public void setClinicCenter(ClinicCenter clinicCenter) {
        this.clinicCenter = clinicCenter;
    }
}
