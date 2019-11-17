package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class AdminClinicCenter extends User {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ClinicCenter clinicCenterA;

    public AdminClinicCenter() {
    }

    public AdminClinicCenter(ClinicCenter clinicCenterA) {
        this.clinicCenterA = clinicCenterA;
    }

    public AdminClinicCenter(Long id, String firstName, String lastName, Role role) {
        super(id, firstName, lastName, role);
    }

    public AdminClinicCenter(Long id, String firstName, String lastName, Role role, ClinicCenter clinicCenterA) {
        super(id, firstName, lastName, role);
        this.clinicCenterA = clinicCenterA;
    }

    public ClinicCenter getClinicCenterA() {
        return clinicCenterA;
    }

    public void setClinicCenterA(ClinicCenter clinicCenterA) {
        this.clinicCenterA = clinicCenterA;
    }
}
