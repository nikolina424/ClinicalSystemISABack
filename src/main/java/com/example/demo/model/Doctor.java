package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "doctor")
public class Doctor extends User{

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Clinic clinic;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Operation operation;

    public Doctor() {
    }

    public Doctor(Clinic clinic) {
        this.clinic = clinic;
    }

    public Doctor(Long id, String firstName, String lastName, String email, String password, String address, String city, String country, Integer phoneNumber, Integer userId, Role role) {
        super(id, firstName, lastName, email, password, address, city, country, phoneNumber, userId, role);
    }

    public Doctor(Long id, String firstName, String lastName, String email, String password, String address, String city, String country, Integer phoneNumber, Integer userId, Role role, Clinic clinic, Room room, Operation operation) {
        super(id, firstName, lastName, email, password, address, city, country, phoneNumber, userId, role);
        this.clinic = clinic;
        this.room = room;
        this.operation = operation;
    }

    public Doctor(Long id, String firstName, String lastName, Role role, Clinic clinic) {
        super(id, firstName, lastName, role);
        this.clinic = clinic;
    }

    public Doctor(Long id, String firstName, String lastName, Role role, Clinic clinic, Room room, Operation operation) {
        super(id, firstName, lastName, role);
        this.clinic = clinic;
        this.room = room;
        this.operation = operation;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
}
