package com.example.demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ClinicCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "clinicCenter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Clinic> clinicList = new ArrayList<Clinic>();

    @OneToMany(mappedBy = "clinicCenterA", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AdminClinicCenter> adminClinicCenterList = new ArrayList<>();

    public ClinicCenter() {
    }

    public ClinicCenter(Long id, String name, List<Clinic> clinicList, List<AdminClinicCenter> adminClinicCenterList) {
        this.id = id;
        this.name = name;
        this.clinicList = clinicList;
        this.adminClinicCenterList = adminClinicCenterList;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Clinic> getClinicList() {
        return clinicList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClinicList(List<Clinic> clinicList) {
        this.clinicList = clinicList;
    }

    public List<AdminClinicCenter> getAdminClinicCenterList() {
        return adminClinicCenterList;
    }

    public void setAdminClinicCenterList(List<AdminClinicCenter> adminClinicCenterList) {
        this.adminClinicCenterList = adminClinicCenterList;
    }
}
