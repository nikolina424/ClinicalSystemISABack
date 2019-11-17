package com.example.demo.model;

import javax.persistence.*;
import javax.print.Doc;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", nullable = false)
    private Integer number;

    @Column(name = "free")
    private Boolean free;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Doctor> doctorList = new ArrayList<Doctor>();

    @OneToOne
    @JoinColumn(name = "operationId")
    private Operation operationRoom;

    public Room() {
    }

    public Room(Long id, Integer number, Boolean free, List<Doctor> doctorList) {
        this.id = id;
        this.number = number;
        this.free = free;
        this.doctorList = doctorList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Boolean getFree() {
        return free;
    }

    public void setFree(Boolean free) {
        this.free = free;
    }

    public List<Doctor> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }
}
