package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class AdminClinic extends User {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Clinic clinicA;

    public AdminClinic() {
    }

    public AdminClinic(Clinic clinicA) {
        this.clinicA = clinicA;
    }

    public AdminClinic(Long id, String firstName, String lastName, Role role) {
        super(id, firstName, lastName, role);
    }

    public AdminClinic(Long id, String firstName, String lastName, Role role, Clinic clinicA) {
        super(id, firstName, lastName, role);
        this.clinicA = clinicA;
    }

    public Clinic getClinicA() {
        return clinicA;
    }

    public void setClinicA(Clinic clinicA) {
        this.clinicA = clinicA;
    }
}
