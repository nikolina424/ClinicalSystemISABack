package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class MedicalSister extends User {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Clinic clinicM;

    public MedicalSister() {
    }

    public MedicalSister(Clinic clinic) {
        this.clinicM = clinic;
    }

    public MedicalSister(Long id, String firstName, String lastName, String email, String password, String address, String city, String country, Integer phoneNumber, Integer userId, Role role) {
        super(id, firstName, lastName, email, password, address, city, country, phoneNumber, userId, role);
    }

    public MedicalSister(Long id, String firstName, String lastName, String email, String password, String address, String city, String country, Integer phoneNumber, Integer userId, Role role, Clinic clinicM) {
        super(id, firstName, lastName, email, password, address, city, country, phoneNumber, userId, role);
        this.clinicM = clinicM;
    }

    public MedicalSister(Long id, String firstName, String lastName, Role role, Clinic clinic) {
        super(id, firstName, lastName, role);
        this.clinicM = clinic;
    }

    public Clinic getClinic() {
        return clinicM;
    }

    public void setClinic(Clinic clinic) {
        this.clinicM = clinic;
    }
}
