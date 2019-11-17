package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Patient extends User{

    @OneToOne
    @JoinColumn(name = "medicalRecordId")
    private MedicalRecord medicalRecord;

    @OneToOne
    @JoinColumn(name = "operationId")
    private Operation operationPatient;

    public Patient() {
    }

    public Patient(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public Patient(Long id, String firstName, String lastName, String email, String password, String address, String city, String country, Integer phoneNumber, Integer userId, Role role) {
        super(id, firstName, lastName, email, password, address, city, country, phoneNumber, userId, role);
    }

    public Patient(Long id, String firstName, String lastName, String email, String password, String address, String city, String country, Integer phoneNumber, Integer userId, Role role, MedicalRecord medicalRecord, Operation operationPatient) {
        super(id, firstName, lastName, email, password, address, city, country, phoneNumber, userId, role);
        this.medicalRecord = medicalRecord;
        this.operationPatient = operationPatient;
    }

    public Patient(Long id, String firstName, String lastName, Role role, MedicalRecord medicalRecord) {
        super(id, firstName, lastName, role);
        this.medicalRecord = medicalRecord;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }
}
