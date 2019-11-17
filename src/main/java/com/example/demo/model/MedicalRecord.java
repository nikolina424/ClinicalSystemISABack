package com.example.demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "medicalRecord")
    private Patient patient;

    @OneToMany(mappedBy = "medRecord", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Visit> visitList = new ArrayList<Visit>();

    @ManyToMany
    @JoinTable(
            name = "patientSick",
            joinColumns = @JoinColumn(name = "med_record_id"),
            inverseJoinColumns = @JoinColumn(name = "sick_id")
    )
    private List<Sick> sickList = new ArrayList<Sick>();

    public MedicalRecord() {
    }

    public MedicalRecord(Long id, Patient patient, List<Visit> visitList, List<Sick> sickList) {
        this.id = id;
        this.patient = patient;
        this.visitList = visitList;
        this.sickList = sickList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Visit> getVisitList() {
        return visitList;
    }

    public void setVisitList(List<Visit> visitList) {
        this.visitList = visitList;
    }

    public List<Sick> getSickList() {
        return sickList;
    }

    public void setSickList(List<Sick> sickList) {
        this.sickList = sickList;
    }
}
