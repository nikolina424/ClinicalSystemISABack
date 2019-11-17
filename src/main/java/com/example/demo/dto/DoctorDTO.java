package com.example.demo.dto;


import com.example.demo.model.Doctor;

public class DoctorDTO {

    private Long id;
    private String email;
    private String password;

    public DoctorDTO() {
    }

    public DoctorDTO(Doctor doctor) {
        this(doctor.getId(), doctor.getFirstName(), doctor.getPassword());
    }

    public DoctorDTO(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
